package com.example.volunteersite.entities.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OfferDto {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String date;
}
