package com.example.volunteersite.service;

import com.example.volunteersite.dto.PsychoDto;
import com.example.volunteersite.dto.UserDto;
import com.example.volunteersite.repositories.PsychologistRepository;
import com.example.volunteersite.repositories.UserRepository;
import com.example.volunteersite.user.Psychologist;
import com.example.volunteersite.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PsychologistService {
    @Autowired
    private PsychologistRepository userRepository;
    @Autowired
    private UserRepository userRepositoryImpl;
    public Optional<Psychologist> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<Psychologist> findAllUsers(){
        return userRepository.findAll();
    }

    public void editUser(PsychoDto userDto){
        Psychologist user = userRepository.findById(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setCity(userDto.getCity());
        user.setPhone(userDto.getPhone());
        user.setAbout(userDto.getAbout());
        userRepository.save(user);
    }

    public void deleteUser(long id){
        Psychologist user = userRepository.findById(id);
        userRepository.delete(user);
    }
    public Psychologist findById(long id){
        return userRepository.findById(id);
    }
    
}
