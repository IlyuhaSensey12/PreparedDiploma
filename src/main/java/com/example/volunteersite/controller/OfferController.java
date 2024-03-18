package com.example.volunteersite.controller;

import com.example.volunteersite.entities.dto.OfferDto;
import com.example.volunteersite.entities.models.Offer;
import com.example.volunteersite.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;
    @PostMapping("/offerReg")
    public void addOffer(@RequestBody OfferDto offer){
        offerService.addOffer(offer);
    }

    @GetMapping("/offerList")
    public List<Offer> findAll(){
        return offerService.findAll();
    }


    @PostMapping("/zayava")
    public void addOff(@RequestBody OfferDto offerDto){
        offerService.addOff(offerDto);
    }

}
