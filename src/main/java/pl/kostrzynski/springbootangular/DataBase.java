package pl.kostrzynski.springbootangular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataBase {
    private DataSource dataSource;

    @Autowired
    public DataBase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public void init() {
        String sql = "CREATE TABLE user(id int, nick varchar(255) ,password varchar(255), PRIMARY KEY(id))";
        getJdbcTemplate().update(sql);
    }

}
