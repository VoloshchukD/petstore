package by.tms.petstore.config;

import by.tms.petstore.model.Order;
import by.tms.petstore.model.Pet;
import by.tms.petstore.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "by.tms.petstore")
public class AppConfig {

    @Bean("map")
    public Map<String, Integer> map(){
        return new HashMap<>();
    }

    @Bean("orders")
    public List<Order> orders(){
        return new ArrayList<>();
    }

    @Bean("users")
    public List<User> users(){
        return new ArrayList<>();
    }

    @Bean("currentUser")
    public User userValid(){
        return new User();
    }

}