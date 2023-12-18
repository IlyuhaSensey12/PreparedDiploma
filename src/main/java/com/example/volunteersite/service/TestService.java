package com.example.volunteersite.service;

import com.example.volunteersite.entities.dto.TestResultDto;
import com.example.volunteersite.entities.models.TestResult;
import com.example.volunteersite.entities.models.User;
import com.example.volunteersite.repositories.TestResultRepository;
import com.example.volunteersite.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private final TestResultRepository testResultRepository;
    @Autowired
    private UserRepository userRepository;

    public TestService(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }
    public String analyzeScore(int score) {

        if (score >= 1 && score <= 3) {
            return "Низкий уровень стресса или тревоги.";
        } else if (score >= 4 && score <= 7) {
            return "Умеренный уровень стресса или тревоги. Рекомендуется поверхностная консультация.";
        } else if (score >= 8 && score <= 10) {
            return "Высокий уровень стресса или тревоги. Рекомендуется обратиться к профессионалу.";
        } else if (score == 0) {
            return "Уровень стресса на нуле";
        }
        else {
            return "Некорректный результат теста.";
        }

    }

    public TestResult submitTest(TestResultDto testResultDto) {
        String interpretation = analyzeScore(testResultDto.getScore());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = (String) authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(currentUserName);

        TestResult result = new TestResult();
        result.setScore(testResultDto.getScore());
        result.setInterpretation(interpretation);
        result.setUser(userOptional.get());
        return testResultRepository.save(result);
    }

    // Остальные методы остаются без изменений
}
