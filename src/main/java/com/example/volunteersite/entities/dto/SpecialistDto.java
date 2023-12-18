package com.example.volunteersite.entities.dto;

import com.example.volunteersite.entities.models.User;
import lombok.Data;

import java.util.List;

@Data
public class SpecialistDto {
    private long id;

    private String firstname;

    private String lastname;

    private String email;

    private String city;

    private String phone;

    private String password;

    private String about;

    private List<User> users;
}
