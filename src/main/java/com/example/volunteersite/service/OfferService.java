package com.example.volunteersite.service;

import com.example.volunteersite.entities.dto.OfferDto;
import com.example.volunteersite.entities.models.Offer;
import com.example.volunteersite.entities.models.User;
import com.example.volunteersite.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserServiceImpl userService;

    public void addOffer(OfferDto offer){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = (String) authentication.getName();

        Optional<User> user = userService.findByEmail(currentUserName);

        Offer offer1 = new Offer();
        offer1.setName(user.get().getFirstname());
        offer1.setPhone(offer.getPhone());
        offer1.setAddress(offer.getAddress());
        offer1.setDate(offer.getDate());
        offerRepository.save(offer1);
    }

    public void addOff(OfferDto offer){
        Offer offer1 = new Offer();
        offer1.setName(offer.getName());
        offer1.setPhone(offer.getPhone());
        offerRepository.save(offer1);
    }

    public List<Offer> findAll(){
        return offerRepository.findAll();
    }
}
