package com.jobpilot.jobpilot_backend.jobfetch.source.impl;

import com.jobpilot.jobpilot_backend.jobfetch.dto.JobSourceDTO;
import com.jobpilot.jobpilot_backend.jobfetch.source.NaukriJobFetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaukriJobFetcherImpl
        implements NaukriJobFetcher {

    @Override
    public List<JobSourceDTO> fetchJobs() {

        return List.of();
    }
}