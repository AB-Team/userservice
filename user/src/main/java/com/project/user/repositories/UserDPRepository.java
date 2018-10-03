package com.project.user.repositories;

import com.project.user.models.UserDP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDPRepository extends MongoRepository<UserDP, String> {

    public List<UserDP> findByUsername(String username);
}
