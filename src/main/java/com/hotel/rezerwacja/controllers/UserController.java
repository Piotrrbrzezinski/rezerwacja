package com.hotel.rezerwacja.controllers;

import com.hotel.rezerwacja.DTO.HotelRoomDTO;
import com.hotel.rezerwacja.DTO.UserDTO;
import com.hotel.rezerwacja.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable("email") String email) {

        Optional<UserDTO> userDTO = userService.findByEmail(email);

        if (userDTO.isPresent()) {
            return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> userDTOS = userService.findAll();
        if (userDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createHotelRoom(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUserByEmail(@PathVariable("id") String emial) {

        try {
            userService.deleteByEmail(emial);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteUserRooms() {
        try {
            userService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/userbooking/{email}")
    public ResponseEntity<HttpStatus> booking(@PathVariable String email, @RequestBody HotelRoomDTO hotelRoom) {

        try{
            userService.booking(email,hotelRoom.getNumberRoom());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/removeBooking/{email}")
    public ResponseEntity<HttpStatus> removeBooking(@PathVariable String email, @RequestBody HotelRoomDTO hotelRoom) {

        try{
            userService.removeBooking(email,hotelRoom.getNumberRoom());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
