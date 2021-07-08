package com.hotel.rezerwacja.DAO;

import com.hotel.rezerwacja.DTO.UserDTO;
import com.hotel.rezerwacja.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO  extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
