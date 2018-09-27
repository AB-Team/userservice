package com.project.user.repositories;

import com.project.user.models.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findUserById(int id);

    public List<User> findAll(Pageable pageable);

    public void deleteById(int id);
}
