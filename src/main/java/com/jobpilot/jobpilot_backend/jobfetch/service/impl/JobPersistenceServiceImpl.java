package com.jobpilot.jobpilot_backend.jobfetch.service.impl;

import com.jobpilot.jobpilot_backend.job.entity.Job;
import com.jobpilot.jobpilot_backend.job.repository.JobRepository;
import com.jobpilot.jobpilot_backend.jobfetch.dto.JobSourceDTO;
import com.jobpilot.jobpilot_backend.jobfetch.service.JobPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPersistenceServiceImpl
        implements JobPersistenceService {

    private final JobRepository jobRepository;

    @Override
    public void saveJobs(List<JobSourceDTO> jobs) {

        for (JobSourceDTO jobDto : jobs) {

            if (jobRepository.findByExternalJobId(
                    jobDto.getSource()
                            + "_"
                            + jobDto.getJobUrl()
            ).isPresent()) {

                continue;
            }

            Job job = Job.builder()
                    .title(jobDto.getTitle())
                    .company(jobDto.getCompany())
                    .location(jobDto.getLocation())
                    .description(jobDto.getDescription())
                    .jobUrl(jobDto.getJobUrl())
                    .source(jobDto.getSource())
                    .externalJobId(
                            jobDto.getSource()
                                    + "_"
                                    + jobDto.getJobUrl()
                    )
                    .createdAt(LocalDateTime.now())
                    .lastSeen(LocalDateTime.now())
                    .build();

            jobRepository.save(job);
        }
    }
}
