package com.example.volunteersite.controller;

import com.example.volunteersite.entities.dto.UserDto;
import com.example.volunteersite.service.UserServiceImpl;
import com.example.volunteersite.entities.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:5173")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/showAllUsers")
    public List<User> showUserData(){
        return userService.findAllUsers();
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody UserDto userDto){
        userService.editUser(userDto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }


    @GetMapping("/getUser")
    public Optional<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return userService.findByEmail(currentUserName);
    }

}
