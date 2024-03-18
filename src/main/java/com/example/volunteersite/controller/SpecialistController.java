package com.example.volunteersite.controller;

import com.example.volunteersite.entities.dto.SpecialistDto;
import com.example.volunteersite.service.SpecialistService;
import com.example.volunteersite.entities.models.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:5173/")
public class SpecialistController {
    @Autowired
    private SpecialistService userService;

    @GetMapping("/getPsycho")
    public Optional<Specialist> getPsycho() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentOrg = authentication.getName();
        return userService.findByEmail(currentOrg);
    }
    @GetMapping("/findPsychoById/{id}")
    public Specialist showPsycho(@PathVariable long id){
        return userService.findById(id);
    }

    @GetMapping("/showAll")
    public List<Specialist> showUserData(){
        return userService.findAllUsers();
    }

    @PutMapping("/updatePsycho")
    public void updateUser(@RequestBody SpecialistDto orgDto){
        userService.editUser(orgDto);
    }

    @DeleteMapping("/deletePsycho/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }


}
