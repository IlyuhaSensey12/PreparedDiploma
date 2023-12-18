package com.example.volunteersite.entities.requests;

import lombok.Data;

@Data
public class RegisterPsychoRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String city;
    private String phone;
    private String about;
}
