package com.lesson.springdatajpa.service;

import com.lesson.springdatajpa.model.Booking;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking updateBooking(String bookingId, Booking booking);
    void deleteBooking(String bookingId);
    Booking getBooking(String bookingId);
}
