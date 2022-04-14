package com.lesson.springdatajpa.repository;

import com.lesson.springdatajpa.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    @Query("select u from User u where u.firstName like :firstName")
    List<User> getUsersByFirstName(@Param("firstName") String firstName);
}
