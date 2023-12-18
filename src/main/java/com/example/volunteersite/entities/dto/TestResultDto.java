package com.example.volunteersite.entities.dto;

import com.example.volunteersite.entities.models.User;
import lombok.Data;

@Data
public class TestResultDto {
    private Long id;
    private int score;
    private String interpretation;
    private User user;
}
