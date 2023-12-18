package com.example.volunteersite.entities.dto;

import com.example.volunteersite.entities.models.Specialist;
import com.example.volunteersite.entities.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class VisitDto {
    private Long id;

    private User user;

    private Specialist specialist;

    private String time;
    private boolean isApproved = false;
}
