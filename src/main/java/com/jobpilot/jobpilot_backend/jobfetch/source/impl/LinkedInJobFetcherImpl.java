package com.jobpilot.jobpilot_backend.jobfetch.source.impl;

import com.jobpilot.jobpilot_backend.jobfetch.dto.JobSourceDTO;
import com.jobpilot.jobpilot_backend.jobfetch.source.LinkedInJobFetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkedInJobFetcherImpl
        implements LinkedInJobFetcher {

//    @Override
//    public List<JobSourceDTO> fetchJobs() {
//
//        return List.of();
//    }
@Override
public List<JobSourceDTO> fetchJobs() {

    return List.of(
            JobSourceDTO.builder()
                    .title("Java Developer")
                    .company("TCS")
                    .location("Hyderabad")
                    .description("Spring Boot Developer")
                    .jobUrl("https://test-job-1.com")
                    .source("LINKEDIN")
                    .build()
    );
}
}