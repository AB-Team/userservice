package com.project.user.controller;

import com.project.user.models.User;
import com.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    UserService userService;

    @RequestMapping("/find/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.findUserById(id);
    }

    @RequestMapping("/find/users")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @RequestMapping("/remove/{id}")
    public boolean deleteUserById(@PathVariable("id") int id){
        try {
            userService.deleteUserById(id);
        }catch(Exception ex){
            return false;
        }

        return true;
    }
}
