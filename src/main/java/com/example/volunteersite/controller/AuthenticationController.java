package com.example.volunteersite.controller;

import com.example.volunteersite.auth.*;
import com.example.volunteersite.auth.RegisterOrgRequest;
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
            @RequestBody RegisterOrgRequest request
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
