package com.jobpilot.jobpilot_backend.dashboard.service.impl;

import com.jobpilot.jobpilot_backend.application.repository.ApplicationRepository;
import com.jobpilot.jobpilot_backend.dashboard.dto.DashboardResponse;
import com.jobpilot.jobpilot_backend.dashboard.service.DashboardService;
import com.jobpilot.jobpilot_backend.job.service.JobService;
import com.jobpilot.jobpilot_backend.job.dto.JobMatchResponse;
import com.jobpilot.jobpilot_backend.job.repository.JobRepository;
import com.jobpilot.jobpilot_backend.skill.repository.SkillRepository;
import com.jobpilot.jobpilot_backend.user.entity.User;
import com.jobpilot.jobpilot_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final SkillRepository skillRepository;
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobService jobService;

    @Override
    public DashboardResponse getDashboard() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        long totalSkills =
                skillRepository.findByUserId(user.getId()).size();

        long totalApplications =
                applicationRepository.findByUserId(user.getId()).size();

        long totalJobs =
                jobRepository.count();

        int bestMatchScore =
                jobService.getMatchedJobs()
                        .stream()
                        .map(JobMatchResponse::getMatchScore)
                        .max(Integer::compareTo)
                        .orElse(0);

        return DashboardResponse.builder()
                .totalSkills(totalSkills)
                .totalApplications(totalApplications)
                .totalJobs(totalJobs)
                .bestMatchScore(bestMatchScore)
                .build();
    }
}