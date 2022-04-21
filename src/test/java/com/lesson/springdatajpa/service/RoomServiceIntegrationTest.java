package com.lesson.springdatajpa.service;

import com.lesson.springdatajpa.config.PostgreSQLContainerInitializer;
import com.lesson.springdatajpa.model.Room;
import com.lesson.springdatajpa.model.RoomType;
import com.lesson.springdatajpa.repository.RoomRepository;
import com.lesson.springdatajpa.service.exception.RequiredFieldMissedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = {PostgreSQLContainerInitializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoomServiceIntegrationTest {
    private static final String TEST_ROOM_NUMBER = "test";
    private static final Integer TEST_ROOM_FLOOR = 1;
    private static final String TEST_ROOM_DESCRIPTION = "test";
    private static final Integer TEST_ROOM_PRICE = 1000;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    void shouldCreateRoom() {
        Room room = new Room(
                null,
                TEST_ROOM_NUMBER,
                TEST_ROOM_FLOOR,
                RoomType.ECONOM,
                TEST_ROOM_DESCRIPTION,
                TEST_ROOM_PRICE
        );
        room = roomService.create(room);
        Assertions.assertEquals(room, getRoomByIdOrThrowException(room.getId()));
    }

    @Test
    void shouldNotCreateRoomWithoutRoomNumber() {
        Room room = new Room(
                null,
                null,
                TEST_ROOM_FLOOR,
                RoomType.ECONOM,
                TEST_ROOM_DESCRIPTION,
                TEST_ROOM_PRICE
        );
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> roomService.create(room));
    }

    @Test
    void shouldNotCreateRoomWithoutRoomType() {
        Room room = new Room(
                null,
                TEST_ROOM_NUMBER,
                TEST_ROOM_FLOOR,
                null,
                TEST_ROOM_DESCRIPTION,
                TEST_ROOM_PRICE
        );
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> roomService.create(room));
    }

    @Test
    void shouldNotCreateRoomWithoutFloor() {
        Room room = new Room(
                null,
                TEST_ROOM_NUMBER,
                null,
                RoomType.ECONOM,
                TEST_ROOM_DESCRIPTION,
                TEST_ROOM_PRICE
        );
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> roomService.create(room));
    }

    @Test
    void shouldNotCreateRoomWithoutPrice() {
        Room room = new Room(
                null,
                TEST_ROOM_NUMBER,
                TEST_ROOM_FLOOR,
                RoomType.ECONOM,
                TEST_ROOM_DESCRIPTION,
                null
        );
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> roomService.create(room));
    }

    @Test
    void shouldCreateRoomWithUnnecessaryParams() {
        Room room = new Room(
                null,
                TEST_ROOM_NUMBER,
                TEST_ROOM_FLOOR,
                RoomType.ECONOM,
                null,
                TEST_ROOM_PRICE
        );
        Assertions.assertDoesNotThrow(() -> roomService.create(room));
    }

    private Room getRoomByIdOrThrowException(String id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    private Room getRoomByIdOrNull(String id) {
        return roomRepository.findById(id).orElse(null);
    }
}
