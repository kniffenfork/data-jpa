package com.lesson.springdatajpa.service.impl;

import com.lesson.springdatajpa.model.Room;
import com.lesson.springdatajpa.model.RoomType;
import com.lesson.springdatajpa.repository.RoomRepository;
import com.lesson.springdatajpa.service.RoomService;
import com.lesson.springdatajpa.service.exception.RequiredFieldMissedException;
import com.lesson.springdatajpa.service.exception.room.RoomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room room) {
        validate(room);
        Room createdRoom = new Room(
                UUID.randomUUID().toString(),
                room.getRoomNumber(),
                room.getFloor(),
                room.getRoomType(),
                room.getDescription(),
                room.getPrice()
        );
        return roomRepository.save(createdRoom);
    }

    @Override
    public Room update(String id, Room room) {
        Room oldRoom = getBy(id);
        Room updatedRoom = getUpdatedRoom(oldRoom, room);
        return roomRepository.save(updatedRoom);
    }

    @Override
    public void delete(String id) {
        roomRepository.delete(getBy(id));
    }

    @Override
    public Room getBy(String id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Override
    public List<Room> getAllBy(Integer priceFrom, Integer priceTo) {
        return roomRepository.findAllByPriceBetween(priceFrom, priceTo);
    }

    private void validate(Room room) {
        if (room.getRoomNumber() == null) throw new RequiredFieldMissedException("roomNumber");
        if (room.getRoomType() == null) throw new RequiredFieldMissedException("roomType");
        if (room.getFloor() == null) throw new RequiredFieldMissedException("floor");
        if (room.getPrice() == null) throw new RequiredFieldMissedException("price");
    }

    private Room getUpdatedRoom(Room oldRoom, Room updateParams) {
        String newRoomNumber = updateParams.getRoomNumber();
        RoomType newRoomType = updateParams.getRoomType();
        Integer newFloor = updateParams.getFloor();
        String newDescription = updateParams.getDescription();
        Integer newPrice = updateParams.getPrice();
        if (updateParams.getPrice() == null) newPrice = oldRoom.getPrice();
        if (updateParams.getRoomType() == null) newRoomType = oldRoom.getRoomType();
        if (updateParams.getRoomNumber() == null) newRoomNumber = oldRoom.getRoomNumber();
        if (updateParams.getFloor() == null) newFloor = oldRoom.getFloor();
        if (updateParams.getDescription() == null) newDescription = oldRoom.getDescription();
        return new Room(oldRoom.getId(), newRoomNumber, newFloor, newRoomType, newDescription, newPrice);
    }
}
