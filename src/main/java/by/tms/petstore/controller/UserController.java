package by.tms.petstore.controller;

import by.tms.petstore.exception.petException.PetNotFoundException;
import by.tms.petstore.exception.userException.InvalidPasswordException;
import by.tms.petstore.exception.userException.InvalidUsernameException;
import by.tms.petstore.exception.userException.UserNotFoundException;
import by.tms.petstore.model.*;
import by.tms.petstore.serviсe.StoreService;
import by.tms.petstore.serviсe.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> findUser(@PathVariable("username") @NotNull String username){
       if(userService.findUser(username).equals(null)){
           throw new UserNotFoundException();
       }
       return new ResponseEntity(userService.findUser(username),HttpStatus.OK);
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("username") @NotNull String username, @RequestBody @NotNull User user){
       if(userService.updUser(username, user)){
           return new ResponseEntity(new ApiResponse("Successfully updated"),HttpStatus.OK);
       }
        throw new UserNotFoundException();
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("username") @NotNull String username){
        if(userService.delUser(username)){
                return new ResponseEntity(new ApiResponse("Successfully deleted"), HttpStatus.OK);
        }
        throw new UserNotFoundException();
    }

    @GetMapping(path = "/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestParam @NotNull String username, @RequestParam @NotNull String password){
       if(userService.login(username, password)){
            return new ResponseEntity(new ApiResponse("Success login"), HttpStatus.OK);
       }
       throw new UserNotFoundException();
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<ApiResponse> logoutUser(){
        if(userService.logout()){
            return new ResponseEntity(new ApiResponse("Success logout"), HttpStatus.OK);
        }
        throw new UserNotFoundException();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user){
        switch (userService.addUser(user)){
            case "added" : return new ResponseEntity(new ApiResponse("Successfully added"), HttpStatus.OK);
            case "userexists" : return new ResponseEntity(new ApiResponse("User exists"), HttpStatus.BAD_REQUEST);
            case "notfound" : throw new UserNotFoundException();
        }
        return new ResponseEntity(new ApiResponse("something wrong"), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostMapping(path = "/createWithArray")
    public ResponseEntity<ApiResponse> createWithArray(@RequestBody @NotNull User[] usersP){
        if(!userService.createWithArray(usersP)){
            return new ResponseEntity(new ApiResponse("User exists"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ApiResponse("Successfully created"), HttpStatus.OK);
    }

    @PostMapping(path = "/createWithList")
    public ResponseEntity<ApiResponse> createWithArray(@RequestBody List<User> usersP){
        if(!userService.createWithList(usersP)){
            return new ResponseEntity(new ApiResponse("User exists"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ApiResponse("Successfully created"), HttpStatus.OK);
    }

}
