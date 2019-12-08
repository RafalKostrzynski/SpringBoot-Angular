package pl.kostrzynski.springbootangular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    UserRepo userRepo;

     @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
        createUser();
    }

    @Override
    public void createUser() {
        User user1=new User("rafx4g","12345d3333g");
        User user2=new User("rasd23g","122221111g");
        User user3=new User("rwaf23g","14444s2222g");
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
    }
}
