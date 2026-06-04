package com.jobpilot.jobpilot_backend.resume.controller;

import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import com.jobpilot.jobpilot_backend.resume.dto.ResumeResponse;
import com.jobpilot.jobpilot_backend.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ApiResponse<ResumeResponse> uploadResume(
            @RequestParam("file") MultipartFile file) {

        ResumeResponse response =
                resumeService.uploadResume(file);

        return ApiResponse.<ResumeResponse>builder()
                .success(true)
                .message("Resume uploaded successfully")
                .data(response)
                .build();
    }
}