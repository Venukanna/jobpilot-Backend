package com.jobpilot.jobpilot_backend.resume.dto.analysis;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectDto {

    private String title;
    private String description;
    private List<String> technologies;
}