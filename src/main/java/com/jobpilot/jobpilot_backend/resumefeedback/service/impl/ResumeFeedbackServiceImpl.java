package com.jobpilot.jobpilot_backend.resumefeedback.service.impl;

import com.jobpilot.jobpilot_backend.job.dto.JobMatchResponse;
import com.jobpilot.jobpilot_backend.job.service.JobService;
import com.jobpilot.jobpilot_backend.resumefeedback.dto.ResumeFeedbackResponse;
import com.jobpilot.jobpilot_backend.resumefeedback.service.ResumeFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeFeedbackServiceImpl
        implements ResumeFeedbackService {

    private final JobService jobService;

    @Override
    public ResumeFeedbackResponse generateFeedback() {

        List<JobMatchResponse> matchedJobs =
                jobService.getMatchedJobs();

        List<String> strengths = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();
        List<String> suggestions = new ArrayList<>();

        int bestScore = matchedJobs.stream()
                .map(JobMatchResponse::getMatchScore)
                .max(Integer::compareTo)
                .orElse(0);

        if (!matchedJobs.isEmpty()) {

            JobMatchResponse bestMatch = matchedJobs.get(0);

            strengths.addAll(bestMatch.getMatchedSkills());

            missingSkills.addAll(bestMatch.getMissingSkills());
        }

        if (bestScore < 60) {

            suggestions.add(
                    "Add more in-demand backend skills"
            );

            suggestions.add(
                    "Include more real-world projects"
            );

        } else if (bestScore < 80) {

            suggestions.add(
                    "Improve your resume with cloud technologies"
            );

            suggestions.add(
                    "Add internship experience if available"
            );

        } else {

            suggestions.add(
                    "Resume is strong for current job matches"
            );

            suggestions.add(
                    "Add GitHub and LinkedIn links"
            );
        }

        return ResumeFeedbackResponse.builder()
                .resumeScore(bestScore)
                .strengths(strengths)
                .missingSkills(missingSkills)
                .suggestions(suggestions)
                .build();
    }
}