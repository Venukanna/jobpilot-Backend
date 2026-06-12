package com.jobpilot.jobpilot_backend.job.service.impl;

import com.jobpilot.jobpilot_backend.job.dto.JobAnalysisResponse;
import com.jobpilot.jobpilot_backend.job.dto.RecommendedJobResponse;
import com.jobpilot.jobpilot_backend.job.entity.Job;
import com.jobpilot.jobpilot_backend.job.repository.JobRepository;
import com.jobpilot.jobpilot_backend.job.service.JobAnalysisService;
import com.jobpilot.jobpilot_backend.job.service.JobRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobRecommendationServiceImpl
        implements JobRecommendationService {

    private final JobRepository jobRepository;
    private final JobAnalysisService jobAnalysisService;

    @Override
    public List<RecommendedJobResponse> getRecommendedJobs() {

        return jobRepository.findAll()
                .stream()
                .map(job -> {

                    JobAnalysisResponse analysis =
                            jobAnalysisService
                                    .analyzeJob(job.getId());

                    return RecommendedJobResponse.builder()
                            .jobId(job.getId())
                            .title(job.getTitle())
                            .company(job.getCompany())
                            .matchScore(
                                    analysis.getMatchScore()
                            )
                            .build();
                })
                .sorted(
                        (a, b) ->
                                b.getMatchScore()
                                        .compareTo(
                                                a.getMatchScore()
                                        )
                )
                .toList();
    }
}