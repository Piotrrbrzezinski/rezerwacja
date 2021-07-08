package com.hotel.rezerwacja.servicesImpl;

import com.hotel.rezerwacja.DAO.HotelRoomDAO;
import com.hotel.rezerwacja.DAO.UserDAO;
import com.hotel.rezerwacja.DTO.UserDTO;
import com.hotel.rezerwacja.entitys.HotelRoom;
import com.hotel.rezerwacja.entitys.User;
import com.hotel.rezerwacja.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private ModelMapper modelMapper;

    private HotelRoomDAO hotelRoomDAO;

    @Override
    public Optional<UserDTO> findByEmail(String email) {

        Optional<User> user = userDAO.findByEmail(email);
        if(user.isPresent()) {

            Optional<UserDTO> userDTO = Optional.of(modelMapper.map(user.get(), UserDTO.class));
            return userDTO;
        }
        else {

            return Optional.empty();
        }
    }

    @Override
    public void createHotelRoom(final UserDTO userDTO) {

        User user = modelMapper.map(userDTO, User.class);
        userDAO.save(user);
    }

    @Override
    public List<UserDTO> findAll() {

        List<User> hotelRooms = userDAO.findAll();
        List<UserDTO> userDTOList = hotelRooms.stream().map(hotelRoom -> modelMapper.map(hotelRoom,UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public void deleteByEmail(String email) {

        Optional<User> userOptional = userDAO.findByEmail(email);
        userOptional.ifPresent(a ->userDAO.delete(userOptional.get()));
    }

    @Override
    public void deleteAll() {

        userDAO.deleteAll();
    }

    @Override
    public void booking(String emial, int numberRoom) throws Exception {

        Optional<User> user = userDAO.findByEmail(emial);
        Optional<HotelRoom> hotelRoom = hotelRoomDAO.findByNumberRoom(numberRoom);
        if(user.isPresent() && hotelRoom.isPresent()) {

            user.get().addHotelRoom(hotelRoom.get());
            userDAO.save(user.get());
        }
        else {

            throw new Exception();

        }

    }

    @Override
    public void removeBooking(String email, int numberRoom) throws Exception {

        Optional<User> user = userDAO.findByEmail(email);
        Optional<HotelRoom> hotelRoom = hotelRoomDAO.findByNumberRoom(numberRoom);
        if(user.isPresent() && hotelRoom.isPresent()) {

            user.get().removeHotelRoom(hotelRoom.get());
            userDAO.save(user.get());
        }
        else {

            throw new Exception();

        }

    }
}
