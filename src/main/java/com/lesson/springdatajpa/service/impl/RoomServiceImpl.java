package com.lesson.springdatajpa.service.impl;

import com.lesson.springdatajpa.model.Room;
import com.lesson.springdatajpa.repository.RoomRepository;
import com.lesson.springdatajpa.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public Room createRoom(Room room) {
        //TODO: реализовать
        return null;
    }

    @Override
    public Room updateRoom(String id, Room room) {
        //TODO: реализовать
        return null;
    }

    @Override
    public void deleteRoom(String id) {
        //TODO: реализовать
    }

    @Override
    public Room getRoom(String id) {
        //TODO: реализовать
        return null;
    }

    @Override
    public List<Room> getRooms(Integer priceFrom, Integer priceTo) {
        //TODO: реализовать
        return null;
    }
}
