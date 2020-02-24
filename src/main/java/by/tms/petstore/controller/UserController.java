package by.tms.petstore.controller;

import by.tms.petstore.exception.petException.PetNotFoundException;
import by.tms.petstore.exception.userException.InvalidPasswordException;
import by.tms.petstore.exception.userException.InvalidUsernameException;
import by.tms.petstore.exception.userException.UserNotFoundException;
import by.tms.petstore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("users")
    public List<User> users;

    @Autowired
    @Qualifier("currentUser")
    public User currentUser;

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> findUser(@PathVariable("username") String username){
        if(username==null){
            throw new InvalidUsernameException();
        }
        for(User user1: users){
            if(user1.getUsername().equals(username)){
                return new ResponseEntity(user1, HttpStatus.OK);
            } else {
                throw new InvalidUsernameException();
            }
        }
        throw new UserNotFoundException();
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("username") String username, @RequestBody User user){
        if(username==null){
            throw new InvalidUsernameException();
        }
        for(User user1: users){
            if(user1.getUsername().equals(username)){
                users.remove(user1);
                users.add(user);
                return new ResponseEntity(new ApiResponse(200,"Response","Successfully updated"), HttpStatus.OK);
            } else {
                throw new InvalidUsernameException();
            }
        }
        throw new UserNotFoundException();
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("username") String username){
        if(username==null){
            throw new InvalidUsernameException();
        }
        for(User user1: users){
            if(user1.getUsername().equals(username)){
                users.remove(user1);
                return new ResponseEntity(new ApiResponse(200,"Response","Successfully deleted"), HttpStatus.OK);
            } else {
                throw new InvalidUsernameException();
            }
        }
        throw new UserNotFoundException();
    }

    @GetMapping(path = "/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestParam String username, @RequestParam String password){
        if(username==null){
            throw new InvalidUsernameException();
        }
        if(password==null){
            throw new InvalidPasswordException();
        }
        for(User user1: users){
            if(user1.getUsername().equals(username)){
                user1.setUserStatus(User.Status.VALID);
                currentUser = new User(user1.getId(),user1.getUsername(), user1.getFirstName(), user1.getLastName(),
                        user1.getEmail(), user1.getPassword(), user1.getPhone(), user1.getUserStatus());
                return new ResponseEntity(new ApiResponse(200,"Response","Success login"), HttpStatus.OK);
            } else {
                throw new InvalidUsernameException();
            }
        }
        throw new UserNotFoundException();
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<ApiResponse> loginUser(){
        currentUser.setUserStatus(User.Status.INVALID);
        currentUser = null;
        return new ResponseEntity(new ApiResponse(200,"Response","Success logout"), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user){
        if(!currentUser.getUserStatus().equals(User.Status.VALID) || currentUser != null){
            users.add(user);
        }
        if(users.contains(user)){
            throw new InvalidUsernameException();
        }
        return new ResponseEntity(new ApiResponse(200,"Response","Successfully added"), HttpStatus.OK);
    }

    @PostMapping(path = "/createWithArray")
    public ResponseEntity<ApiResponse> createWithArray(@RequestBody User[] usersP){
        for(User user1 : usersP){
            if(users.contains(user1)){
                throw new InvalidUsernameException();
            }
            users.add(user1);
        }
        return new ResponseEntity(new ApiResponse(200,"Response","Successfully created"), HttpStatus.OK);
    }

    @PostMapping(path = "/createWithList")
    public ResponseEntity<ApiResponse> createWithArray(@RequestBody List<User> usersP){
        for(User user1 : usersP){
            if(users.contains(user1)){
                throw new InvalidUsernameException();
            }
            users.add(user1);
        }
        return new ResponseEntity(new ApiResponse(200,"Response","Successfully created"), HttpStatus.OK);
    }

}
