package com.example.volunteersite.dto;

import com.example.volunteersite.user.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDto {

    private long id;

    private String firstname;

    private String lastname;

    private String email;

    private String city;

    private String phone;

    private String password;

}
