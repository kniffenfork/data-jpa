package com.lesson.springdatajpa.service.impl;

import com.lesson.springdatajpa.model.User;
import com.lesson.springdatajpa.repository.UserRepository;
import com.lesson.springdatajpa.service.UserService;
import com.lesson.springdatajpa.service.exception.RequiredFieldMissedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        validate(user);
        User createdUser = new User(
                UUID.randomUUID().toString(),
                user.getPhone(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName()
        );
        return userRepository.save(createdUser);
    }

    private void validate(User user) {
        if (user.getFirstName() == null) throw new RequiredFieldMissedException("firstName");
        if (user.getLastName() == null) throw new RequiredFieldMissedException("lastName");
        if (user.getPhone() == null) throw new RequiredFieldMissedException("phone");
        if (user.getEmail() == null) throw new RequiredFieldMissedException("email");
    }

    @Override
    public User updateUser(String userId, User user) {
        User oldUser = getUser(userId);
        User updatedUser = getUpdatedUser(oldUser, user);
        return userRepository.save(updatedUser);
    }



    private User getUpdatedUser(User oldUser, User updateParams) {
        String newFirstName = updateParams.getFirstName();
        String newLastName = updateParams.getLastName();
        String newMiddleName = updateParams.getMiddleName();
        String newEmail = updateParams.getEmail();
        String newPhone = updateParams.getPhone();
        if (updateParams.getFirstName() == null) newFirstName = oldUser.getFirstName();
        if (updateParams.getLastName() == null) newLastName = oldUser.getLastName();
        if (updateParams.getMiddleName() == null) newMiddleName = oldUser.getMiddleName();
        if (updateParams.getPhone() == null) newPhone = oldUser.getPhone();
        if (updateParams.getEmail() == null) newEmail = oldUser.getEmail();
        if (updateParams.getMiddleName() == null) newMiddleName = oldUser.getMiddleName();
        return new User(oldUser.getId(), newPhone, newEmail, newFirstName, newLastName, newMiddleName);
    }

    @Override
    public void deleteUser(String userId) {
        User userToDelete = getUser(userId);
        userRepository.delete(userToDelete);
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }
}
