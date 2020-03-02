package by.tms.petstore.serviсe;

import by.tms.petstore.exception.userException.InvalidUsernameException;
import by.tms.petstore.model.*;
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

    @Autowired
    private User currentUser;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(String username){
        Optional<User> byId = userRepository.findById(username);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    public boolean updUser(String username, User user) {
        Optional<User> byId = userRepository.findById(username);
        if(byId.isPresent()){
            byId.get().setUserStatus(user.getUserStatus());
            byId.get().setEmail(user.getEmail());
            byId.get().setPassword(user.getPassword());
            byId.get().setFirstName(user.getFirstName());
            byId.get().setLastName(user.getLastName());
            byId.get().setPhone(user.getPhone());
            return true;
        }
        return false;
    }

    public boolean delUser(String username) {
        Optional<User> byId = userRepository.findById(username);
        if(byId.isPresent()){
            userRepository.delete(byId.get());
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        Optional<User> byId = userRepository.findById(username);
        if(byId.isPresent()){
            byId.get().setUserStatus(User.Status.VALID);
            currentUser = byId.get();
            return true;
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
            userRepository.save(user);
            return "added";
        }
        if(userRepository.existsById(user.getUsername())){
            return "userexists";
        }
       return "notfound";
    }

    public boolean createWithArray(User[] usersP) {
        for(User user1 : usersP){
            if(userRepository.existsById(user1.getUsername())){
                return false;
            }
            userRepository.save(user1);
        }
        return true;
    }

    public boolean createWithList(List<User> usersP) {
        for(User user1 : usersP){
            if(userRepository.existsById(user1.getUsername())){
                return false;
            }
            userRepository.save(user1);
        }
        return true;
    }

}
