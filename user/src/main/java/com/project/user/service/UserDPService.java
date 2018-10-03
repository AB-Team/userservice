package com.project.user.service;

import com.project.user.models.UserDP;
import com.project.user.repositories.UserDPRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDPService {

    @Autowired
    UserDPRepository userDPRepository;

    public List<UserDP> getAllUser(String username){
        return userDPRepository.findByUsername(username);
    };
}
