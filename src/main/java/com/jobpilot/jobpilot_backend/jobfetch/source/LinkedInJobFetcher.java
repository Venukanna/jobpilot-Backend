package com.jobpilot.jobpilot_backend.jobfetch.source;

import com.jobpilot.jobpilot_backend.jobfetch.dto.JobSourceDTO;

import java.util.List;

public interface LinkedInJobFetcher {

    List<JobSourceDTO> fetchJobs();
}