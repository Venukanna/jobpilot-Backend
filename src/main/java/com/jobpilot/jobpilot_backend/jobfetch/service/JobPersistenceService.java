package com.jobpilot.jobpilot_backend.jobfetch.service;

import com.jobpilot.jobpilot_backend.jobfetch.dto.JobSourceDTO;

import java.util.List;

public interface JobPersistenceService {

    void saveJobs(List<JobSourceDTO> jobs);
}