package com.jobpilot.jobpilot_backend.dashboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private Long totalSkills;

    private Long totalApplications;

    private Long totalJobs;

    private Integer bestMatchScore;
}