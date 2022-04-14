package com.lesson.springdatajpa.service;

import com.lesson.springdatajpa.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(User user);
    User updateUser(String userId, User user);
    void deleteUser(String userId);
    User getUser(String userId);
}
