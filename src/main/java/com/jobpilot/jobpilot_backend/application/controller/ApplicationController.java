package com.jobpilot.jobpilot_backend.application.controller;

import com.jobpilot.jobpilot_backend.application.dto.ApplicationResponse;
import com.jobpilot.jobpilot_backend.application.service.ApplicationService;
import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/{jobId}")
    public ApiResponse<Void> applyJob(
            @PathVariable Long jobId) {

        applicationService.applyJob(jobId);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Job applied successfully")
                .data(null)
                .build();
    }

    @GetMapping
    public ApiResponse<List<ApplicationResponse>>
    getMyApplications() {

        return ApiResponse
                .<List<ApplicationResponse>>builder()
                .success(true)
                .message("Applications fetched successfully")
                .data(
                        applicationService
                                .getMyApplications()
                )
                .build();
    }
}