package com.jobpilot.jobpilot_backend.resumefeedback.controller;

import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import com.jobpilot.jobpilot_backend.resumefeedback.dto.ResumeFeedbackResponse;
import com.jobpilot.jobpilot_backend.resumefeedback.service.ResumeFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resume-feedback")
@RequiredArgsConstructor
public class ResumeFeedbackController {

    private final ResumeFeedbackService resumeFeedbackService;

    @GetMapping
    public ApiResponse<ResumeFeedbackResponse> getFeedback() {

        return ApiResponse
                .<ResumeFeedbackResponse>builder()
                .success(true)
                .message("Resume feedback generated successfully")
                .data(
                        resumeFeedbackService.generateFeedback()
                )
                .build();
    }
}