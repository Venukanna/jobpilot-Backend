package com.jobpilot.jobpilot_backend.job.service;

import com.jobpilot.jobpilot_backend.job.dto.RecommendedJobResponse;

import java.util.List;

public interface JobRecommendationService {

    List<RecommendedJobResponse> getRecommendedJobs();
}