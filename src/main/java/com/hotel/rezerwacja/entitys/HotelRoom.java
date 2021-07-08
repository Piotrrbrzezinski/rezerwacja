package com.hotel.rezerwacja.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "HotelRoom")
@Table(name = "Hotel_Room")
public class HotelRoom  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelRoomId;

    @Column(name = "number_room", unique = true, nullable = false)
    private Integer numberRoom;

    @ManyToMany(mappedBy="hotelRooms")
    private List<User> users ;//= new ArrayList<>();
}
