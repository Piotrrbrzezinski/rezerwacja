package com.hotel.rezerwacja.DAO;

import com.hotel.rezerwacja.entitys.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRoomDAO extends JpaRepository<HotelRoom, Integer> {

    Optional<HotelRoom> findByNumberRoom(Integer numberRoom);
}
