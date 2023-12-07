package com.example.volunteersite.dto;

import com.example.volunteersite.user.Role;
import com.example.volunteersite.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class PsychoDto {
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
