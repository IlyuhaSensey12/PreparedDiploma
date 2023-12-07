package com.example.volunteersite.auth;

import com.example.volunteersite.config.JwtService;
import com.example.volunteersite.repositories.PsychologistRepository;
import com.example.volunteersite.user.Psychologist;
import com.example.volunteersite.user.Role;
import com.example.volunteersite.user.User;
import com.example.volunteersite.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PsychologistRepository psychoRepository;

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

    public AuthenticationResponse registerPsycho(RegisterOrgRequest request) throws Exception {
        Psychologist organization = new Psychologist();
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
            organization.setRole(Role.PSYCHOLOGIST);
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
