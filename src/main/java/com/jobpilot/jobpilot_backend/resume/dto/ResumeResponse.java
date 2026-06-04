package com.jobpilot.jobpilot_backend.resume.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeResponse {

    private Long id;

    private String fileName;

    private String filePath;
}