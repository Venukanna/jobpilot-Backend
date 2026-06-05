package com.jobpilot.jobpilot_backend.job.service;

import com.jobpilot.jobpilot_backend.job.dto.JobMatchResponse;
import com.jobpilot.jobpilot_backend.job.dto.JobResponse;

import java.util.List;

public interface JobService {

    List<JobResponse> getAllJobs();
    List<JobMatchResponse> getMatchedJobs();
}