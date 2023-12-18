package com.example.volunteersite.service;

import com.example.volunteersite.entities.dto.SpecialistDto;
import com.example.volunteersite.repositories.SpecialistRepository;
import com.example.volunteersite.repositories.UserRepository;
import com.example.volunteersite.entities.models.Specialist;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialistService {
    @Autowired
    private SpecialistRepository userRepository;
    @Autowired
    private UserRepository userRepositoryImpl;
    public Optional<Specialist> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<Specialist> findAllUsers(){
        return userRepository.findAll();
    }

    public void editUser(SpecialistDto userDto){
        Specialist user = userRepository.findById(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setCity(userDto.getCity());
        user.setPhone(userDto.getPhone());
        user.setAbout(userDto.getAbout());
        userRepository.save(user);
    }

    public void deleteUser(long id){
        Specialist user = userRepository.findById(id);
        userRepository.delete(user);
    }
    public Specialist findById(long id){
        return userRepository.findById(id);
    }
    
}
