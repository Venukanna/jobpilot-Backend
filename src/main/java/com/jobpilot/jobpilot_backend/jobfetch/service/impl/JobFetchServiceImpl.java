package com.jobpilot.jobpilot_backend.jobfetch.service.impl;

import com.jobpilot.jobpilot_backend.jobfetch.dto.JobSourceDTO;
import com.jobpilot.jobpilot_backend.jobfetch.service.JobFetchService;
import com.jobpilot.jobpilot_backend.jobfetch.service.JobPersistenceService;
import com.jobpilot.jobpilot_backend.jobfetch.source.ApnaJobFetcher;
import com.jobpilot.jobpilot_backend.jobfetch.source.LinkedInJobFetcher;
import com.jobpilot.jobpilot_backend.jobfetch.source.NaukriJobFetcher;
import com.jobpilot.jobpilot_backend.jobfetch.source.WorkIndiaJobFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jobpilot.jobpilot_backend.jobfetch.client.JSearchClient;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobFetchServiceImpl implements JobFetchService {

    private final LinkedInJobFetcher linkedInJobFetcher;
    private final NaukriJobFetcher naukriJobFetcher;
    private final ApnaJobFetcher apnaJobFetcher;
    private final WorkIndiaJobFetcher workIndiaJobFetcher;
    private final JobPersistenceService jobPersistenceService;
    private final JSearchClient jSearchClient;


    @Override
    public void fetchJobs() {

        log.info("Starting job fetch process...");

        List<JobSourceDTO> jobs = new ArrayList<>();

        jobs.addAll(jSearchClient.searchJobs());
        System.out.println("Total jobs collected: " + jobs.size());
        jobs.addAll(linkedInJobFetcher.fetchJobs());
        jobs.addAll(naukriJobFetcher.fetchJobs());
        jobs.addAll(apnaJobFetcher.fetchJobs());
        jobs.addAll(workIndiaJobFetcher.fetchJobs());

        jobPersistenceService.saveJobs(jobs);

        log.info(
                "Fetched and saved {} jobs",
                jobs.size()
        );

    }

}