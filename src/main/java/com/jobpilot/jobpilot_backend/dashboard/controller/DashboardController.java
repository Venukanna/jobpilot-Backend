package com.jobpilot.jobpilot_backend.dashboard.controller;

import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import com.jobpilot.jobpilot_backend.dashboard.dto.DashboardResponse;
import com.jobpilot.jobpilot_backend.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ApiResponse<DashboardResponse> getDashboard() {

        return ApiResponse.<DashboardResponse>builder()
                .success(true)
                .message("Dashboard fetched successfully")
                .data(
                        dashboardService.getDashboard()
                )
                .build();
    }
}