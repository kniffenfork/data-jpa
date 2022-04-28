package com.lesson.springdatajpa.service;

import com.lesson.springdatajpa.model.Room;
import com.lesson.springdatajpa.model.RoomType;
import com.lesson.springdatajpa.repository.RoomRepository;
import com.lesson.springdatajpa.service.exception.RequiredFieldMissedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RoomServiceUnitTest {
    private static final String TEST_ROOM_ID = "test";
    private static final String TEST_ROOM_NUMBER = "test";
    private static final Integer TEST_ROOM_FLOOR = 1;
    private static final String TEST_ROOM_DESCRIPTION = "test";
    private static final Integer TEST_ROOM_PRICE = 1000;
    private static final Room TEST_ROOM = new Room(
                                                TEST_ROOM_ID,
                                                TEST_ROOM_NUMBER,
                                                TEST_ROOM_FLOOR,
                                                RoomType.ECONOM,
                                                TEST_ROOM_DESCRIPTION,
                                                TEST_ROOM_PRICE
    );

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

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
        Mockito.when(roomRepository.save(room)).thenReturn(TEST_ROOM);
        Assertions.assertEquals(TEST_ROOM, roomService.create(room));
    }

    @Test
    void shouldNotCreateRoomWithoutNumber() {
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
    void shouldNotCreateRoomWithoutType() {
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
    void shouldCreateRoomWithoutNecessaryFields() {
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
}
