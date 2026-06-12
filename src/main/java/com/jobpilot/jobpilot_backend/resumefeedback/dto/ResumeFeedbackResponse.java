package com.jobpilot.jobpilot_backend.resumefeedback.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResumeFeedbackResponse {

    private Integer resumeScore;

    private List<String> strengths;

    private List<String> missingSkills;

    private List<String> suggestions;
}