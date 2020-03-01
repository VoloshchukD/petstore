package by.tms.petstore.serviсe;

import by.tms.petstore.exception.userException.InvalidUsernameException;
import by.tms.petstore.model.ApiResponse;
import by.tms.petstore.model.Order;
import by.tms.petstore.model.User;
import by.tms.petstore.repository.StoreRepository;
import by.tms.petstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> users = new ArrayList<>();

    public User currentUser = new User();

    public User findUser(String username){
        for(User user1 : users){
            if(user1.getUsername().equals(username)){
                return user1;
            }
        }
        return null;
    }

    public boolean updUser(String username, User user) {
        for (User user1 : users) {
            if (user1.getUsername().equals(username)) {
                user1.setUserStatus(user.getUserStatus());
                user1.setEmail(user.getEmail());
                user1.setFirstName(user.getFirstName());
                user1.setLastName(user.getLastName());
                user1.setPassword(user.getPassword());
                user1.setPhone(user.getPhone());
                return true;
            }
        }
        return false;
    }

    public boolean delUser(String username) {
        for (User user1 : users) {
            if (user1.getUsername().equals(username)) {
               users.remove(user1);
                return true;
            }
        }
        return false;
    }

    public boolean login(String username, String password) {
        for(User user1: users) {
            if (user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
                user1.setUserStatus(User.Status.VALID);
                currentUser = user1;
                return true;
            }
        }
       return false;
    }

    public boolean logout() {
        if(currentUser.equals(null)){
            return false;
        }
        currentUser.setUserStatus(User.Status.INVALID);
        currentUser = null;
        return true;
    }

    public String addUser(User user) {
        if(!currentUser.getUserStatus().equals(User.Status.VALID) || currentUser != null){
            users.add(user);
            return "added";
        }
        if(users.contains(user)){
            return "userexists";
        }
       return "notfound";
    }

    public boolean createWithArray(User[] usersP) {
        for(User user1 : usersP){
            if(users.contains(user1)){
                return false;
            }
            users.add(user1);
        }
        return true;
    }

    public boolean createWithList(List<User> usersP) {
        for(User user1 : usersP){
            if(users.contains(user1)){
                return false;
            }
            users.add(user1);
        }
        return true;
    }

}
