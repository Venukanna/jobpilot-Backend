package com.jobpilot.jobpilot_backend.resume.service;

import com.jobpilot.jobpilot_backend.resume.dto.analysis.ResumeAnalysisResponse;

public interface ResumeAnalysisService {

    ResumeAnalysisResponse analyze(String resumeText);
}