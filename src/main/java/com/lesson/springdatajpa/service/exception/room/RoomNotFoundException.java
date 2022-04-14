package com.lesson.springdatajpa.service.exception.room;

public class RoomNotFoundException extends RoomException{
    public RoomNotFoundException(String id) {
        super("There is no room with id " + id);
    }
}
