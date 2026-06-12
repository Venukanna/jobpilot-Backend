package com.jobpilot.jobpilot_backend.job.controller;

import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import com.jobpilot.jobpilot_backend.job.dto.JobAnalysisResponse;
import com.jobpilot.jobpilot_backend.job.dto.JobResponse;
import com.jobpilot.jobpilot_backend.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.jobpilot.jobpilot_backend.job.dto.JobMatchResponse;
import com.jobpilot.jobpilot_backend.job.service.JobAnalysisService;
import com.jobpilot.jobpilot_backend.job.dto.RecommendedJobResponse;
import com.jobpilot.jobpilot_backend.job.service.JobRecommendationService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final JobAnalysisService jobAnalysisService;
    private final JobRecommendationService jobRecommendationService;

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

    @GetMapping("/{jobId}/analyze")
    public ApiResponse<JobAnalysisResponse>
    analyzeJob(@PathVariable Long jobId) {

        return ApiResponse
                .<JobAnalysisResponse>builder()
                .success(true)
                .message("Job analyzed successfully")
                .data(
                        jobAnalysisService
                                .analyzeJob(jobId)
                )
                .build();
    }

    @GetMapping("/recommended")
    public ApiResponse<List<RecommendedJobResponse>>
    getRecommendedJobs() {

        return ApiResponse
                .<List<RecommendedJobResponse>>builder()
                .success(true)
                .message(
                        "Recommended jobs fetched successfully"
                )
                .data(
                        jobRecommendationService
                                .getRecommendedJobs()
                )
                .build();
    }
}