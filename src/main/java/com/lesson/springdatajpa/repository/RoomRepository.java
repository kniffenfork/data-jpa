package com.lesson.springdatajpa.repository;

import com.lesson.springdatajpa.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, String> {
    List<Room> findAllByPriceBetween(Integer priceFrom, Integer priceTo);
}
