package com.hotel.rezerwacja.servicesImpl;

import com.hotel.rezerwacja.DAO.HotelRoomDAO;
import com.hotel.rezerwacja.DTO.HotelRoomDTO;
import com.hotel.rezerwacja.entitys.HotelRoom;
import com.hotel.rezerwacja.services.HotelRoomService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelRoomServiceImpl implements HotelRoomService {

    private HotelRoomDAO hotelRoomDAO;

    private ModelMapper modelMapper;

    @Override
    public Optional<HotelRoomDTO> findByNumberRoom(int numberRoom) {

        Optional<HotelRoom> hotelRoom = hotelRoomDAO.findByNumberRoom(numberRoom);
        if(hotelRoom.isPresent()) {

            Optional<HotelRoomDTO> hotelRoomDTO = Optional.of(modelMapper.map(hotelRoom.get(), HotelRoomDTO.class));
            return hotelRoomDTO;
        }
        else {

            return Optional.empty();
        }

    }

    @Override
    public void createHotelRoom(final HotelRoomDTO hotelRoomDTO) {

        HotelRoom hotelRoom = modelMapper.map(hotelRoomDTO, HotelRoom.class);
        hotelRoomDAO.save(hotelRoom);
    }

    @Override
    public List<HotelRoomDTO> findAll() {

        List<HotelRoom> hotelRooms = hotelRoomDAO.findAll();
        List<HotelRoomDTO> hotelRoomDTOList = hotelRooms.stream().map(hotelRoom -> modelMapper.map(hotelRoom,HotelRoomDTO.class)).collect(Collectors.toList());
        return hotelRoomDTOList;
    }

    @Override
    public void deleteById(int id) {

        hotelRoomDAO.deleteById(id);
    }

    @Override
    public void deleteAll() {

        hotelRoomDAO.deleteAll();
    }
}
