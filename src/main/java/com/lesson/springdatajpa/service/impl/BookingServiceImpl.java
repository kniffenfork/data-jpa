package com.lesson.springdatajpa.service.impl;

import com.lesson.springdatajpa.model.Booking;
import com.lesson.springdatajpa.repository.BookingRepository;
import com.lesson.springdatajpa.service.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking updateBooking(String bookingId, Booking booking) {
        return null;
    }

    @Override
    public void deleteBooking(String bookingId) {

    }

    @Override
    public Booking getBooking(String bookingId) {
        return null;
    }
}
