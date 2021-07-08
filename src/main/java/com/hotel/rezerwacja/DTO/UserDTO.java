package com.hotel.rezerwacja.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer userId;
    private String email;
    private String firstName;
    private String lastName;

}
