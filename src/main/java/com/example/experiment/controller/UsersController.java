package com.example.experiment.controller;

import com.example.experiment.exceptions.UserValidatorException;
import com.example.experiment.model.User;
import com.example.experiment.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        try {
            usersService.addUser(user);
            System.out.println("Received user: " + user);
            return "User added";
        }
        catch (UserValidatorException e){
            return e.getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/getUser/{userId}")
    public User getUserById(@PathVariable int userId){
        return usersService.getUserById(userId);
    }

    @PutMapping("/updateUser/{userId}")
    public String updateUser(@PathVariable int userId, @RequestBody User user){
        try{
            usersService.updateUser(userId, user);
            return "User updated";}
        catch (UserValidatorException e){
            return e.getMessage();
        }
    }


    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable int userId){
        usersService.deleteUser(userId);
        return "User deleted";
    }
}

