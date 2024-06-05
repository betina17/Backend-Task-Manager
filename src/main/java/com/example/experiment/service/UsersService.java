package com.example.experiment.service;

import com.example.experiment.exceptions.UserValidatorException;
import com.example.experiment.model.Task;
import com.example.experiment.model.User;
import com.example.experiment.repository.IUsersRepository;
import com.example.experiment.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private IUsersRepository usersRepository;

    public User addUser(User user) throws UserValidatorException{
        UserValidation.validateUser(user);
        usersRepository.save(user);
        return user;
    }

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }

    public void deleteUser(int userId){
        usersRepository.deleteById(userId);
    }

    public User updateUser(int userId, User user) throws UserValidatorException {
        UserValidation.validateUser(user);
        Optional<User> userToBeUpdated = usersRepository.findById(userId); //use Optional because obj might not be present in db
        User updatedUser=userToBeUpdated.get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        usersRepository.save(updatedUser);
        return updatedUser;
    }

    // Method to get all tasks of a specific user
    public List<Task> getAllTasksOfUser(int userId) {
        Optional<User> userOptional = usersRepository.findById(userId);
        return userOptional.map(User::getTasks).orElse(null);
    }

    public User getUserById(int userId) {
        return usersRepository.findById(userId).get();
    }


}
