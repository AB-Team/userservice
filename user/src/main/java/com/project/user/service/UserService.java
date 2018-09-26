package com.project.user.service;

import com.project.user.models.User;
import com.project.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(int id){
        return userRepository.findUserById(id);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }
}
