package com.hotel.rezerwacja.services;

import com.hotel.rezerwacja.DTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findByEmail(String email);
    void createHotelRoom(UserDTO userDTO);
    List<UserDTO> findAll();
    void deleteByEmail(String email);

    void deleteAll();

    void booking(String email, int roomNumber) throws Exception;

    void removeBooking (String email, int roomNumber) throws Exception;
}
