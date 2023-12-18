package com.example.volunteersite.controller;

import com.example.volunteersite.entities.dto.TestResultDto;
import com.example.volunteersite.entities.models.TestResult;
import com.example.volunteersite.entities.models.User;
import com.example.volunteersite.repositories.UserRepository;
import com.example.volunteersite.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private final TestService testService;

    @Autowired
    private UserRepository userRepository;
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/submit")
    public void submitTest(@RequestBody TestResultDto testResultDto) {
        testService.submitTest(testResultDto);
    }
}
