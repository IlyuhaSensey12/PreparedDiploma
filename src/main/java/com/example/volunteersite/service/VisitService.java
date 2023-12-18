package com.example.volunteersite.service;

import com.example.volunteersite.entities.dto.VisitDto;
import com.example.volunteersite.entities.models.Specialist;
import com.example.volunteersite.entities.models.User;
import com.example.volunteersite.entities.models.Visit;
import com.example.volunteersite.repositories.SpecialistRepository;
import com.example.volunteersite.repositories.UserRepository;
import com.example.volunteersite.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecialistRepository specialistRepository;

    public void addVisit(VisitDto visitDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = (String) authentication.getName();
        Optional<Specialist> specialistOptional = specialistRepository.findByEmail(currentUserName);
        Visit visit = new Visit();
        visit.setTime(visitDto.getTime());
        visit.setSpecialist(specialistOptional.get());
        visit.setApproved(false);
        visitRepository.save(visit);
    }

    public void addUser(Long id) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = (String) authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(currentUserName);
        Optional<Visit> visit = visitRepository.findById(id);
        if (userOptional.isPresent() && visit.isPresent()) {
            User user = userOptional.get();
            Visit visit1 = visit.get();
            visit1.setUser(user);
            visitRepository.save(visit1);
        } else {
            throw new Exception("Пользователь или специалист не найден");
        }

    }
    public void approving(Long id){
        Optional<Visit> visit = visitRepository.findById(id);
        visit.get().setApproved(true);
        visitRepository.save(visit.get());
    }

    public List<Visit> findAllVisits(){
        return visitRepository.findAll();
    }

}
