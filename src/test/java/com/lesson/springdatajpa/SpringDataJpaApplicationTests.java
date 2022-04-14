package com.lesson.springdatajpa;

import com.lesson.springdatajpa.model.User;
import com.lesson.springdatajpa.repository.BookingRepository;
import com.lesson.springdatajpa.repository.RoomRepository;
import com.lesson.springdatajpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringDataJpaApplicationTests {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateUser() {
        User user = new User(
                UUID.randomUUID().toString(),
                "7000000000",
                "test@mail.ru",
                "Ivan",
                "Ivanov",
                "Ivanovich"
        );
        System.out.println(userRepository.save(user));
    }

    @Test
    void getUserByFirstName() {
        System.out.println(userRepository.getUsersByFirstName("Ivan"));
    }
}
