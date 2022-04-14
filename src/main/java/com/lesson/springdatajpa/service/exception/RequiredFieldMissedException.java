package com.lesson.springdatajpa.service.exception;

public class RequiredFieldMissedException extends RuntimeException {
    public RequiredFieldMissedException(String field) {
        super("There is no field with name " + field);
    }
}
