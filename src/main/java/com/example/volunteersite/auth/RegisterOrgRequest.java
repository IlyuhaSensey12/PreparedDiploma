package com.example.volunteersite.auth;

import lombok.Data;

@Data
public class RegisterOrgRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String city;
    private String phone;
    private String about;
}
