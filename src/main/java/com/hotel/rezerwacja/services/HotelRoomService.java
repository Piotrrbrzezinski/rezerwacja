package com.hotel.rezerwacja.services;

import com.hotel.rezerwacja.DTO.HotelRoomDTO;

import java.util.List;
import java.util.Optional;

public interface HotelRoomService {

    Optional<HotelRoomDTO> findByNumberRoom(int numberRoom);
    void createHotelRoom(HotelRoomDTO hotelRoomDTO);
    List<HotelRoomDTO> findAll();
    void deleteById(int id);

    void deleteAll();
}

