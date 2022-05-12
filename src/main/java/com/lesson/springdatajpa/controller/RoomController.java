package com.lesson.springdatajpa.controller;

import com.lesson.springdatajpa.model.Room;
import com.lesson.springdatajpa.service.RoomService;
import com.lesson.springdatajpa.service.exception.room.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Room room) {
        try {
            Room createdRoom = roomService.create(room);
            return ResponseEntity.ok(createdRoom);
        } catch (RoomNotFoundException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Room room) {
        try {
            Room updatedRoom = roomService.update(id, room);
            return ResponseEntity.ok(updatedRoom);
        } catch (RoomNotFoundException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try {
            roomService.delete(id);
            return ResponseEntity.ok("Successfully deleted room with id =" + id);
        } catch (RoomNotFoundException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(roomService.getBy(id));
        } catch (RoomNotFoundException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
