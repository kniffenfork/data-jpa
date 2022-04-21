package com.lesson.springdatajpa.service;

import com.lesson.springdatajpa.model.Room;
import com.lesson.springdatajpa.model.RoomType;
import com.lesson.springdatajpa.model.User;
import com.lesson.springdatajpa.repository.RoomRepository;
import com.lesson.springdatajpa.repository.UserRepository;
import com.lesson.springdatajpa.service.exception.RequiredFieldMissedException;
import com.lesson.springdatajpa.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class RoomServiceUnitTest {
    private static final String TEST_ROOM_NUMBER = "test";
    private static final Integer TEST_ROOM_FLOOR = 1;
    private static final String TEST_ROOM_DESCRIPTION = "test";
    private static final Integer TEST_ROOM_PRICE = 1000;

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldCreateRoom() {
        Room room = new Room(
                any(),
                TEST_ROOM_NUMBER,
                TEST_ROOM_FLOOR,
                RoomType.ECONOM,
                TEST_ROOM_DESCRIPTION,
                TEST_ROOM_PRICE
        );
        Mockito.when(roomRepository.save(room)).thenReturn(room);
        Assertions.assertEquals(room, roomService.create(room));
    }

    @Test
    void shouldNotCreateRoomWithoutNumber() {
        Room room = new Room(
                anyString(),
                null,
                TEST_ROOM_FLOOR,
                RoomType.ECONOM,
                TEST_ROOM_DESCRIPTION,
                TEST_ROOM_PRICE
        );
        Mockito.when(roomRepository.save(room)).thenReturn(room);
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> roomService.create(room));
    }

    @Test
    void shouldNotCreateRoomWithoutFloor() {
        Room room = new Room(
                anyString(),
                TEST_ROOM_NUMBER,
                null,
                RoomType.ECONOM,
                TEST_ROOM_DESCRIPTION,
                TEST_ROOM_PRICE
        );
        Mockito.when(roomRepository.save(room)).thenReturn(room);
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> roomService.create(room));
    }

    @Test
    void shouldNotCreateRoomWithoutType() {
        Room room = new Room(
                anyString(),
                TEST_ROOM_NUMBER,
                TEST_ROOM_FLOOR,
                null,
                TEST_ROOM_DESCRIPTION,
                TEST_ROOM_PRICE
        );
        Mockito.when(roomRepository.save(room)).thenReturn(room);
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> roomService.create(room));
    }

    @Test
    void shouldNotCreateRoomWithoutPrice() {
        Room room = new Room(
                anyString(),
                TEST_ROOM_NUMBER,
                TEST_ROOM_FLOOR,
                RoomType.ECONOM,
                TEST_ROOM_DESCRIPTION,
                null
        );
        Mockito.when(roomRepository.save(room)).thenReturn(room);
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> roomService.create(room));
    }

    @Test
    void shouldCreateRoomWithoutNecessaryFields() {
        Room room = new Room(
                anyString(),
                TEST_ROOM_NUMBER,
                TEST_ROOM_FLOOR,
                RoomType.ECONOM,
                null,
                TEST_ROOM_PRICE
        );
        Mockito.when(roomRepository.save(room)).thenReturn(room);
        Assertions.assertDoesNotThrow(() -> roomService.create(room));
    }

    @Test
    void shouldThrowException() {
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>(List.of(new User("test", "test", "test", "Ivan", "test", "test"))));
        UserService userService = new UserServiceImpl(userRepository);
        Assertions.assertThrows(RuntimeException.class, userService::getAll);
    }

    @Test
    void shouldGetAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>(List.of(new User("test", "test", "test", "test", "test", "test"))));
        UserService userService = new UserServiceImpl(userRepository);
        Assertions.assertDoesNotThrow(userService::getAll);
    }
}
