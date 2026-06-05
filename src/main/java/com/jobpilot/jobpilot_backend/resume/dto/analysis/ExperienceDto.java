package com.jobpilot.jobpilot_backend.resume.dto.analysis;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExperienceDto {

    private String company;
    private String role;
    private String duration;
    private String description;
}