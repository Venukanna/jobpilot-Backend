package com.jobpilot.jobpilot_backend.job.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JobAnalysisResponse {

    private Long jobId;

    private String title;

    private String company;

    private Integer matchScore;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private List<String> jdSkills;
}