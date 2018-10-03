package com.project.user.controller;

import com.project.user.models.User;
import com.project.user.models.UserDP;
import com.project.user.repositories.UserDPRepository;
import com.project.user.service.UserDPService;
import com.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    UserService userService;

//    @Autowired
//    UserDPService userDPService;

    @Autowired
    UserDPRepository userDPRepository;

    @RequestMapping("/find/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.findUserById(id);
    }

    @RequestMapping("/find/users")
    public List<User> getAllUsers(){
        PageRequest pageRequest = PageRequest.of(0, 4, Sort.by("username").ascending());
        return userService.findAllUsers(pageRequest);
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

    @PostMapping("/upload/{username}")
    public String upload(@PathVariable("username") String username, @RequestParam("file") MultipartFile file){

        UserDP userDP = new UserDP();
        userDP.setUsername(username);
        userDP.setFileId(username+"1");
        userDP.setFile(file);

        userDPRepository.insert(userDP);

        return "true";
    }
}
