package com.example.volunteersite.repositories;

import com.example.volunteersite.entities.models.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult ,Long> {
}
