package com.project.user.repositories;

import com.project.user.models.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findUserById(int id);

    public List<User> findAll(Pageable pageable);

    public void deleteById(int id);

    @Modifying
    @Transactional
    @Query("update User set fileId = :id where username = :username")
    public int updateFileId(@Param("username")String username, @Param("id")String id);
}
