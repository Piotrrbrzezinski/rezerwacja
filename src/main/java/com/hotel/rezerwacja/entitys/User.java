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
@Entity(name = "User")
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "EMAIL") })
public class User implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
    private String lastName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="User_HotelRoom", joinColumns={@JoinColumn(referencedColumnName="userId")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="hotelRoomId")})
    private List<HotelRoom> hotelRooms; //= new ArrayList<>();

    public void addHotelRoom(HotelRoom hotelRoom) {

        hotelRooms.add(hotelRoom);
        hotelRoom.getUsers().add(this);
    }

    public void removeHotelRoom(HotelRoom hotelRoom) {

        hotelRooms.remove(hotelRoom);
        hotelRoom.getUsers().remove(this);
    }

}
