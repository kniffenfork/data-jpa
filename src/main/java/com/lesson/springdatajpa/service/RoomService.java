package com.lesson.springdatajpa.service;

import com.lesson.springdatajpa.model.Room;

import java.util.List;

public interface RoomService {
    Room create(Room room);
    Room update(String id, Room room);
    void delete(String id);
    Room getBy(String id);
    List<Room> getAllBy(
            Integer priceFrom,
            Integer priceTo
    );
}
