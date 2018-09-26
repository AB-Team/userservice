package com.project.user.controller;

import com.project.user.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping("/")
    public User getUserById(){

        User user = new User();
        user.setId(123);
        user.setUserId("user123");
        user.setUsername("username");

        return user;
    }
}
