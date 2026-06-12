package com.jobpilot.jobpilot_backend.jobfetch.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobSourceDTO {

    private String title;

    private String company;

    private String location;

    private String description;

    private String jobUrl;

    private String source;
}