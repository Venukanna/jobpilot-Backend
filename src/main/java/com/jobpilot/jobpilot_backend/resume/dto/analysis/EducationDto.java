package com.jobpilot.jobpilot_backend.resume.dto.analysis;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationDto {

    private String degree;
    private String institution;
    private String year;
}