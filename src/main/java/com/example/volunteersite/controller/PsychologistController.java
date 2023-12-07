package com.example.volunteersite.controller;

import com.example.volunteersite.dto.PsychoDto;
import com.example.volunteersite.service.PsychologistService;
import com.example.volunteersite.user.Psychologist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:3000")
public class PsychologistController {
    @Autowired
    private PsychologistService userService;

    @GetMapping("/getPsycho")
    public Optional<Psychologist> getPsycho() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentOrg = authentication.getName();
        return userService.findByEmail(currentOrg);
    }
    @GetMapping("/findPsychoById/{id}")
    public Psychologist showPsycho(@PathVariable long id){
        return userService.findById(id);
    }

    @GetMapping("/showAll")
    public List<Psychologist> showUserData(){
        return userService.findAllUsers();
    }

    @PutMapping("/updatePsycho")
    public void updateUser(@RequestBody PsychoDto orgDto){
        userService.editUser(orgDto);
    }

    @DeleteMapping("/deletePsycho/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }


}
