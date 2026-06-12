package com.jobpilot.jobpilot_backend.savedjob.service.impl;

import com.jobpilot.jobpilot_backend.common.exception.BadRequestException;
import com.jobpilot.jobpilot_backend.job.entity.Job;
import com.jobpilot.jobpilot_backend.job.repository.JobRepository;
import com.jobpilot.jobpilot_backend.savedjob.dto.SavedJobResponse;
import com.jobpilot.jobpilot_backend.savedjob.entity.SavedJob;
import com.jobpilot.jobpilot_backend.savedjob.repository.SavedJobRepository;
import com.jobpilot.jobpilot_backend.savedjob.service.SavedJobService;
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
public class SavedJobServiceImpl implements SavedJobService {

    private final SavedJobRepository savedJobRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public void saveJob(Long jobId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new BadRequestException("Job not found"));

        if (savedJobRepository.existsByUserIdAndJobId(
                user.getId(),
                jobId
        )) {

            throw new BadRequestException(
                    "Job already saved"
            );
        }

        SavedJob savedJob = SavedJob.builder()
                .user(user)
                .job(job)
                .savedAt(LocalDateTime.now())
                .build();

        savedJobRepository.save(savedJob);
    }

    @Override
    public void removeSavedJob(Long jobId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        savedJobRepository.deleteByUserIdAndJobId(
                user.getId(),
                jobId
        );
    }

    @Override
    public List<SavedJobResponse> getSavedJobs() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return savedJobRepository.findByUserId(user.getId())
                .stream()
                .map(savedJob ->
                        SavedJobResponse.builder()
                                .savedJobId(savedJob.getId())
                                .title(savedJob.getJob().getTitle())
                                .company(savedJob.getJob().getCompany())
                                .location(savedJob.getJob().getLocation())
                                .build()
                )
                .toList();
    }
}