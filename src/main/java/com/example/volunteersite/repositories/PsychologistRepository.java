package com.example.volunteersite.repositories;

import com.example.volunteersite.user.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    Optional<Psychologist> findByEmail(String email);

    Psychologist findById(long id);

}
