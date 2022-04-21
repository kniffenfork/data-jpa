package com.lesson.springdatajpa.service.impl;

import com.lesson.springdatajpa.model.User;
import com.lesson.springdatajpa.repository.UserRepository;
import com.lesson.springdatajpa.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
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

    @Override
    public List<User> getAll() {
        Iterable<User> allUsersIterable = userRepository.findAll();
        List<User> allUsers = new ArrayList<>();
        allUsersIterable.iterator().forEachRemaining(allUsers::add);
        if (allUsers.get(0).getFirstName().equals("Ivan")) {
            throw new RuntimeException("user cant be named as Ivan");
        } else return allUsers;
    }
}
