package com.jobpilot.jobpilot_backend.resume.service;

import com.jobpilot.jobpilot_backend.resume.dto.ResumeResponse;
import com.jobpilot.jobpilot_backend.resume.dto.analysis.ResumeAnalysisResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    ResumeResponse uploadResume(MultipartFile file);
    ResumeAnalysisResponse analyzeResume();
}