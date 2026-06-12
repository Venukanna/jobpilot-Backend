package com.jobpilot.jobpilot_backend.job.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendedJobResponse {

    private Long jobId;

    private String title;

    private String company;

    private Integer matchScore;
}