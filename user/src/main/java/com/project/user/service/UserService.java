package com.project.user.service;

import com.project.user.models.User;
import com.project.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(int id){
        return userRepository.findUserById(id);
    }

    public List<User> findAllUsers(PageRequest pageRequest){
        return userRepository.findAll(pageRequest);
    }

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }
}
