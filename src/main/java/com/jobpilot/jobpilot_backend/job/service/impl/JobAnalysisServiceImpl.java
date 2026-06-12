//package com.jobpilot.jobpilot_backend.job.service.impl;
//
//import com.jobpilot.jobpilot_backend.job.dto.JobAnalysisResponse;
//import com.jobpilot.jobpilot_backend.job.entity.Job;
//import com.jobpilot.jobpilot_backend.job.repository.JobRepository;
//import com.jobpilot.jobpilot_backend.job.service.JobAnalysisService;
//import com.jobpilot.jobpilot_backend.skill.entity.Skill;
//import com.jobpilot.jobpilot_backend.skill.repository.SkillRepository;
//import com.jobpilot.jobpilot_backend.user.entity.User;
//import com.jobpilot.jobpilot_backend.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class JobAnalysisServiceImpl
//        implements JobAnalysisService {
//
//    private final JobRepository jobRepository;
//    private final UserRepository userRepository;
//    private final SkillRepository skillRepository;
//
//    @Override
//    public JobAnalysisResponse analyzeJob(Long jobId) {
//
//        Authentication authentication =
//                SecurityContextHolder.getContext()
//                        .getAuthentication();
//
//        String email = authentication.getName();
//
//        User user = userRepository.findByEmail(email)
//                .orElseThrow();
//
//        Job job = jobRepository.findById(jobId)
//                .orElseThrow();
//
//        List<String> resumeSkills =
//                skillRepository.findByUserId(user.getId())
//                        .stream()
//                        .map(Skill::getSkillName)
//                        .map(String::toLowerCase)
//                        .toList();
//
//        String jdText =
//                ((job.getTitle() == null ? "" : job.getTitle())
//                        + " "
//                        + (job.getDescription() == null
//                        ? ""
//                        : job.getDescription()))
//                        .toLowerCase();
//
//        List<String> jdSkills =
//                Arrays.stream(
//                                jdText.replaceAll("[^a-zA-Z0-9 ]", " ")
//                                        .split("\\s+")
//                        )
//                        .filter(word -> word.length() > 2)
//                        .distinct()
//                        .toList();
//
//        List<String> matchedSkills =
//                resumeSkills.stream()
//                        .filter(jdText::contains)
//                        .toList();
//
//        List<String> missingSkills =
//                jdSkills.stream()
//                        .filter(skill ->
//                                !resumeSkills.contains(skill))
//                        .toList();
//
//        int score = 0;
//
//        if (!resumeSkills.isEmpty()
//                && !jdSkills.isEmpty()) {
//
//            score =
//                    (matchedSkills.size() * 100)
//                            / jdSkills.size();
//        }
//
//        return JobAnalysisResponse.builder()
//                .jobId(job.getId())
//                .title(job.getTitle())
//                .company(job.getCompany())
//                .matchScore(score)
//                .matchedSkills(matchedSkills)
//                .missingSkills(missingSkills)
//                .jdSkills(jdSkills)
//                .build();
//    }
//}

package com.jobpilot.jobpilot_backend.job.service.impl;

import com.jobpilot.jobpilot_backend.job.dto.JobAnalysisResponse;
import com.jobpilot.jobpilot_backend.job.entity.Job;
import com.jobpilot.jobpilot_backend.job.repository.JobRepository;
import com.jobpilot.jobpilot_backend.job.service.JobAnalysisService;
import com.jobpilot.jobpilot_backend.skill.entity.Skill;
import com.jobpilot.jobpilot_backend.skill.repository.SkillRepository;
import com.jobpilot.jobpilot_backend.user.entity.User;
import com.jobpilot.jobpilot_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobAnalysisServiceImpl
        implements JobAnalysisService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    private static final List<String> TECH_KEYWORDS = List.of(
            "java",
            "spring boot",
            "spring",
            "hibernate",
            "jpa",
            "mysql",
            "postgresql",
            "mongodb",
            "react",
            "javascript",
            "typescript",
            "html",
            "css",
            "git",
            "github",
            "docker",
            "kubernetes",
            "aws",
            "azure",
            "gcp",
            "kafka",
            "redis",
            "microservices",
            "rest api",
            "rest",
            "sql",
            "nosql",
            "jenkins",
            "devops",
            "maven",
            "gradle",
            "openai",
            "llm",
            "python"
    );

    @Override
    public JobAnalysisResponse analyzeJob(Long jobId) {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Job job = jobRepository.findById(jobId)
                .orElseThrow();

        List<String> resumeSkills =
                skillRepository.findByUserId(user.getId())
                        .stream()
                        .map(Skill::getSkillName)
                        .map(String::toLowerCase)
                        .distinct()
                        .toList();

        String jdText =
                (
                        (job.getTitle() == null ? "" : job.getTitle())
                                + " "
                                + (job.getDescription() == null
                                ? ""
                                : job.getDescription())
                ).toLowerCase();

        List<String> jdSkills =
                TECH_KEYWORDS.stream()
                        .filter(jdText::contains)
                        .distinct()
                        .toList();

        List<String> matchedSkills =
                resumeSkills.stream()
                        .filter(jdSkills::contains)
                        .toList();

        List<String> missingSkills =
                jdSkills.stream()
                        .filter(skill ->
                                !resumeSkills.contains(skill))
                        .toList();

        int score = 0;

        if (!jdSkills.isEmpty()) {

            score =
                    (matchedSkills.size() * 100)
                            / jdSkills.size();
        }

        return JobAnalysisResponse.builder()
                .jobId(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .matchScore(score)
                .matchedSkills(matchedSkills)
                .missingSkills(missingSkills)
                .jdSkills(jdSkills)
                .build();
    }
}