package com.lesson.springdatajpa.service.impl;

import com.lesson.springdatajpa.model.User;
import com.lesson.springdatajpa.repository.UserRepository;
import com.lesson.springdatajpa.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(String userId, User user) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User getUser(String userId) {
        return null;
    }
}
