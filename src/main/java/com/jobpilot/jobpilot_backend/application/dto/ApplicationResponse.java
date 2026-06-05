package com.jobpilot.jobpilot_backend.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponse {

    private Long applicationId;

    private String jobTitle;

    private String company;

    private String status;
}