package com.example.volunteersite.controller;

import com.example.volunteersite.auth.service.AuthenticationService;
import com.example.volunteersite.entities.requests.AuthenticationPsychoRequest;
import com.example.volunteersite.entities.requests.AuthenticationRequest;
import com.example.volunteersite.entities.requests.RegisterPsychoRequest;
import com.example.volunteersite.entities.requests.RegisterRequest;
import com.example.volunteersite.entities.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/registerPsycho")
    public ResponseEntity<AuthenticationResponse> registerPsycho(
            @RequestBody RegisterPsychoRequest request
    ) throws Exception {
        return ResponseEntity.ok(service.registerPsycho(request));
    }

    @PostMapping("/authenticatePsycho")
    public ResponseEntity<AuthenticationResponse> authenticatePsycho(
            @RequestBody AuthenticationPsychoRequest request
    ){
        return ResponseEntity.ok(service.authenticatePsycho(request));
    }



}
