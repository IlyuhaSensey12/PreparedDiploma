package com.example.volunteersite.controller;

import com.example.volunteersite.entities.dto.VisitDto;
import com.example.volunteersite.entities.models.Visit;
import com.example.volunteersite.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:5173")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @PostMapping("/addVisit")
    public void addVisit(@RequestBody VisitDto visitDto){
        visitService.addVisit(visitDto);
    }
    @PostMapping("/addUserOnSpecialist")
    public void addUser(@RequestBody VisitDto visitDto) throws Exception {
        visitService.addUser(visitDto.getId());
    }
    @PostMapping("/approve")
    public void approve(@RequestBody VisitDto visitDto){
        visitService.approving(visitDto.getId());
    }

    @GetMapping("/listVisits")
    public List<Visit> showAllVisits(){
        return visitService.findAllVisits();
    }

}
