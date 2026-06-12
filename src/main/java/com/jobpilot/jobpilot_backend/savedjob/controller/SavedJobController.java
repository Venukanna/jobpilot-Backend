package com.jobpilot.jobpilot_backend.savedjob.controller;

import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import com.jobpilot.jobpilot_backend.savedjob.dto.SavedJobResponse;
import com.jobpilot.jobpilot_backend.savedjob.service.SavedJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saved-jobs")
@RequiredArgsConstructor
public class SavedJobController {

    private final SavedJobService savedJobService;

    @PostMapping("/{jobId}")
    public ApiResponse<Void> saveJob(
            @PathVariable Long jobId) {

        savedJobService.saveJob(jobId);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Job saved successfully")
                .data(null)
                .build();
    }

    @DeleteMapping("/{jobId}")
    public ApiResponse<Void> removeSavedJob(
            @PathVariable Long jobId) {


        savedJobService.removeSavedJob(jobId);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Saved job removed successfully")
                .data(null)
                .build();
    }

    @GetMapping
    public ApiResponse<List<SavedJobResponse>> getSavedJobs() {

        return ApiResponse
                .<List<SavedJobResponse>>builder()
                .success(true)
                .message("Saved jobs fetched successfully")
                .data(savedJobService.getSavedJobs())
                .build();
    }

}