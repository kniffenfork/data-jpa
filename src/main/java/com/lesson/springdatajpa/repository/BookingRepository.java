package com.lesson.springdatajpa.repository;

import com.lesson.springdatajpa.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, String> {
}
