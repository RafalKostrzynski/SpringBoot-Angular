package pl.kostrzynski.springbootangular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
public class UserControler {
    private UserRepo userRepo;

    @Autowired
    public UserControler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUsersById(@PathVariable Long id) {
        if (userRepo.findById(id).isPresent())
            return new ResponseEntity<>(userRepo.findById(id), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping
    public ResponseEntity<List<User>> modifyCars(@RequestBody User newUser) {
        if (userRepo.findById(newUser.getId()).isPresent()) {
            User user = userRepo.getOne(newUser.getId());
            user.setNick(newUser.getNick());
            user.setPassword(newUser.getPassword());
            userRepo.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long id) {
        if (userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
