package com.jobpilot.jobpilot_backend.job.service;

import com.jobpilot.jobpilot_backend.job.dto.JobAnalysisResponse;

public interface JobAnalysisService {

    JobAnalysisResponse analyzeJob(Long jobId);
}