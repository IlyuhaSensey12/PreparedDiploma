package com.example.volunteersite.entities.dto;

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
