package com.jobpilot.jobpilot_backend.jobfetch.controller;

import com.jobpilot.jobpilot_backend.jobfetch.dto.JobSourceDTO;
import com.jobpilot.jobpilot_backend.jobfetch.client.JSearchClient;
import com.jobpilot.jobpilot_backend.jobfetch.service.JobFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobFetchController {

    private final JSearchClient jSearchClient;
    private final JobFetchService jobFetchService;

    @GetMapping("/api/v1/test-jsearch")
    public List<JobSourceDTO> testJSearch() {

        return jSearchClient.searchJobs();
    }

    @GetMapping("/api/v1/fetch-jobs")
    public String fetchJobs() {

        jobFetchService.fetchJobs();

        return "Jobs fetched successfully";
    }
}