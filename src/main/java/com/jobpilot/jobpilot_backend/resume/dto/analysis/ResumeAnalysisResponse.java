package com.jobpilot.jobpilot_backend.resume.dto.analysis;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResumeAnalysisResponse {

    private String name;

    private String email;

    private String phone;

    private String profileSummary;

    private List<String> skills;

    private List<EducationDto> education;

    private List<ExperienceDto> experiences;

    private List<ProjectDto> projects;
}