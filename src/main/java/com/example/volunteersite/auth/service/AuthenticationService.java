package com.example.volunteersite.auth.service;

import com.example.volunteersite.auth.jwt.service.JwtService;
import com.example.volunteersite.entities.requests.AuthenticationPsychoRequest;
import com.example.volunteersite.entities.requests.AuthenticationRequest;
import com.example.volunteersite.entities.requests.RegisterPsychoRequest;
import com.example.volunteersite.entities.requests.RegisterRequest;
import com.example.volunteersite.entities.responses.AuthenticationResponse;
import com.example.volunteersite.repositories.SpecialistRepository;
import com.example.volunteersite.entities.models.Specialist;
import com.example.volunteersite.entities.enums.Role;
import com.example.volunteersite.entities.models.User;
import com.example.volunteersite.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final SpecialistRepository psychoRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .city(request.getCity())
                .role(Role.USER)
                .dateOfRegistry(new Date(System.currentTimeMillis()))
                .build();
        if(psychoRepository.findByEmail(request.getEmail()).equals(request.getEmail())){
            throw new Exception("We have same user's and organization's email");
        }
        else if (repository.findByEmail(request.getEmail()).equals(request.getEmail())){
            throw new Exception("We have user with the same email");
        }
        else {
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerPsycho(RegisterPsychoRequest request) throws Exception {
        Specialist organization = new Specialist();
        if(repository.findByEmail(request.getEmail()).equals(request.getEmail())){
            throw new Exception("We have same user's and organization's email");
        }
        else if(psychoRepository.findByEmail(request.getEmail()).equals(request.getEmail())){
            throw new Exception("We have organization with the same email");
        }
        else if(!repository.findByEmail(request.getEmail()).equals(request.getEmail()) && !psychoRepository.findByEmail(request.getEmail()).equals(request.getEmail())){
            organization.setFirstname(request.getFirstname());
            organization.setLastname(request.getLastname());
            organization.setEmail(request.getEmail());
            organization.setPhone(request.getPhone());
            organization.setPassword(passwordEncoder.encode(request.getPassword()));
            organization.setRole(Role.SPECIALIST);
            organization.setAbout(request.getAbout());
            organization.setCity(request.getCity());

            psychoRepository.save(organization);
        }
        var jwtToken = jwtService.generateToken(organization);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticatePsycho(AuthenticationPsychoRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var organization = psychoRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(organization);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
