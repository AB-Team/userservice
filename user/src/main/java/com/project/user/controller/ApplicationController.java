package com.project.user.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.project.user.models.User;
import com.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    UserService userService;

    @Autowired
    GridFsOperations gridFsOperations;

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

        DBObject metaData = new BasicDBObject();
        metaData.put("username", username);

        try {
            String id = gridFsOperations.store(file.getInputStream(), file.getName(), file.getContentType(), metaData).toString();

            if(id != null && !id.isEmpty()){
                userService.updateFileId(username, id);
            }

            return id;
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return "Error";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("fileId") String fileId){

        GridFSFile gridFSFile = gridFsOperations.findOne(new Query(Criteria.where("_id").is(fileId)));

        GridFsResource resource = gridFsOperations.getResource(gridFSFile.getFilename());

        try{
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(resource.getContentType())).body(resource);
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.noContent().build();
        }

    }
}
