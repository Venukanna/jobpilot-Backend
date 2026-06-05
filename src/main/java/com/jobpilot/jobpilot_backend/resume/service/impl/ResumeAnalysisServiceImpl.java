package com.jobpilot.jobpilot_backend.resume.service.impl;

import com.jobpilot.jobpilot_backend.resume.dto.analysis.ResumeAnalysisResponse;
import com.jobpilot.jobpilot_backend.resume.service.ResumeAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ResumeAnalysisServiceImpl
        implements ResumeAnalysisService {

    private static final List<String> SKILLS = List.of(
            "Java",
            "Spring Boot",
            "Spring MVC",
            "Hibernate",
            "JPA",
            "REST API",
            "MySQL",
            "PostgreSQL",
            "MongoDB",
            "React",
            "JavaScript",
            "TypeScript",
            "HTML",
            "CSS",
            "Git",
            "GitHub",
            "Docker",
            "Kubernetes",
            "AWS",
            "Azure",
            "Kafka",
            "Redis",
            "JUnit",
            "Mockito",
            "Maven",
            "Gradle"
    );

    @Override
    public ResumeAnalysisResponse analyze(String resumeText) {

        String email = extractEmail(resumeText);

        String phone = extractPhone(resumeText);

        List<String> skills =
                extractSkills(resumeText);

        return ResumeAnalysisResponse.builder()
                .email(email)
                .phone(phone)
                .skills(skills)
                .build();
    }

    private String extractEmail(String text) {

        Pattern pattern = Pattern.compile(
                "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+"
        );

        Matcher matcher =
                pattern.matcher(text);

        return matcher.find()
                ? matcher.group()
                : null;
    }

    private String extractPhone(String text) {

        Pattern pattern = Pattern.compile(
                "\\b\\d{10}\\b"
        );

        Matcher matcher =
                pattern.matcher(text);

        return matcher.find()
                ? matcher.group()
                : null;
    }

    private List<String> extractSkills(String text) {

        List<String> skills =
                new ArrayList<>();

        String lowerText =
                text.toLowerCase();

        for (String skill : SKILLS) {

            if (lowerText.contains(
                    skill.toLowerCase())) {

                skills.add(skill);
            }
        }

        return skills;
    }
}