package com.jobpilot.jobpilot_backend.application.service.impl;

import com.jobpilot.jobpilot_backend.application.dto.ApplicationResponse;
import com.jobpilot.jobpilot_backend.application.entity.Application;
import com.jobpilot.jobpilot_backend.application.entity.ApplicationStatus;
import com.jobpilot.jobpilot_backend.application.repository.ApplicationRepository;
import com.jobpilot.jobpilot_backend.application.service.ApplicationService;
import com.jobpilot.jobpilot_backend.common.exception.BadRequestException;
import com.jobpilot.jobpilot_backend.job.entity.Job;
import com.jobpilot.jobpilot_backend.job.repository.JobRepository;
import com.jobpilot.jobpilot_backend.user.entity.User;
import com.jobpilot.jobpilot_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl
        implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public void applyJob(Long jobId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new BadRequestException("Job not found"));

        if (applicationRepository.existsByUserIdAndJobId(
                user.getId(),
                jobId
        )) {

            throw new BadRequestException(
                    "Already applied for this job"
            );
        }

        Application application =
                Application.builder()
                        .user(user)
                        .job(job)
                        .status(ApplicationStatus.APPLIED)
                        .appliedAt(LocalDateTime.now())
                        .build();

        applicationRepository.save(application);
    }

    @Override
    public List<ApplicationResponse> getMyApplications() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return applicationRepository
                .findByUserId(user.getId())
                .stream()
                .map(application ->
                        ApplicationResponse.builder()
                                .applicationId(
                                        application.getId()
                                )
                                .jobTitle(
                                        application.getJob().getTitle()
                                )
                                .company(
                                        application.getJob().getCompany()
                                )
                                .status(
                                        application.getStatus().name()
                                )
                                .build()
                )
                .toList();
    }
}