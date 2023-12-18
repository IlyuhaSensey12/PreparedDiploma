package com.example.volunteersite.service;

import com.example.volunteersite.entities.dto.UserDto;
import com.example.volunteersite.repositories.UserRepository;
import com.example.volunteersite.entities.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void editUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setCity(userDto.getCity());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
    }

    public void deleteUser(long id){
        User user = userRepository.findById(id);
        userRepository.delete(user);
    }

}

