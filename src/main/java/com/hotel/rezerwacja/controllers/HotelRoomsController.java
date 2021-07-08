package com.hotel.rezerwacja.controllers;

import com.hotel.rezerwacja.DTO.HotelRoomDTO;
import com.hotel.rezerwacja.services.HotelRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class HotelRoomsController {

    private HotelRoomService hotelRoomService;

    @GetMapping("/room/{numberRoom}")
    public ResponseEntity<HotelRoomDTO> getHotelRoomByID(@PathVariable("numberRoom") int numberRoom) {

        Optional<HotelRoomDTO> hotelRoomDTO = hotelRoomService.findByNumberRoom(numberRoom);

        if (hotelRoomDTO.isPresent()) {
            return new ResponseEntity<>(hotelRoomDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<HotelRoomDTO>> getAllHotelRooms() {

        List<HotelRoomDTO> hotelRoomDTOS = hotelRoomService.findAll();
        if (hotelRoomDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(hotelRoomDTOS, HttpStatus.OK);
    }

    @PostMapping("/room")
    public ResponseEntity<HotelRoomDTO> createHotelRoom(@RequestBody HotelRoomDTO hotelRoom) {
        try {
            hotelRoomService.createHotelRoom(hotelRoom);
            return new ResponseEntity<>(hotelRoom, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<HttpStatus> deleteHotelRoomById(@PathVariable("id") int id) {

        try {
            hotelRoomService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/rooms")
    public ResponseEntity<HttpStatus> deleteHotelRooms() {
        try {
            hotelRoomService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
