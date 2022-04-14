package com.lesson.springdatajpa.service;

import com.lesson.springdatajpa.model.Room;
import com.lesson.springdatajpa.model.RoomType;

import java.util.List;

public interface RoomService {
    Room createRoom(Room room);
    Room updateRoom(String id, Room room);
    void deleteRoom(String id);
    Room getRoom(String id);
    List<Room> getRooms(
            Integer priceFrom,
            Integer priceTo
    );
}
