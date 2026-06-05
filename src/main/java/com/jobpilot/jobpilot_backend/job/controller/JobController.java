package com.jobpilot.jobpilot_backend.job.controller;

import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import com.jobpilot.jobpilot_backend.job.dto.JobResponse;
import com.jobpilot.jobpilot_backend.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.jobpilot.jobpilot_backend.job.dto.JobMatchResponse;


import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ApiResponse<List<JobResponse>> getJobs() {

        return ApiResponse.<List<JobResponse>>builder()
                .success(true)
                .message("Jobs fetched successfully")
                .data(jobService.getAllJobs())
                .build();
    }
    @GetMapping("/matches")
    public ApiResponse<List<JobMatchResponse>>
    getMatchedJobs() {

        return ApiResponse
                .<List<JobMatchResponse>>builder()
                .success(true)
                .message("Matched jobs fetched successfully")
                .data(jobService.getMatchedJobs())
                .build();
    }
}