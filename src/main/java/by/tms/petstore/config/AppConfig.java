package by.tms.petstore.config;

import by.tms.petstore.model.Order;
import by.tms.petstore.model.Pet;
import by.tms.petstore.model.User;
import by.tms.petstore.repository.PetRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

@Configuration
@ComponentScan(basePackages = "by.tms.petstore")
public class AppConfig {

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