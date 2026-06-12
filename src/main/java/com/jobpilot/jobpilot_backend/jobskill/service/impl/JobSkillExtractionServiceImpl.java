package com.jobpilot.jobpilot_backend.jobskill.service.impl;

import com.jobpilot.jobpilot_backend.job.entity.Job;
import com.jobpilot.jobpilot_backend.jobskill.entity.JobSkill;
import com.jobpilot.jobpilot_backend.jobskill.repository.JobSkillRepository;
import com.jobpilot.jobpilot_backend.jobskill.service.JobSkillExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSkillExtractionServiceImpl
        implements JobSkillExtractionService {

    private final JobSkillRepository jobSkillRepository;

    private static final List<String> KNOWN_SKILLS =
            List.of(
                    "java",
                    "spring",
                    "spring boot",
                    "hibernate",
                    "mysql",
                    "postgresql",
                    "rest api",
                    "microservices",
                    "aws",
                    "docker",
                    "kubernetes",
                    "kafka",
                    "redis",
                    "mongodb",
                    "react",
                    "angular",
                    "javascript",
                    "python"
            );

    @Override
    public void extractAndSave(Job job) {

        String text =
                (job.getTitle() + " "
                        + job.getDescription())
                        .toLowerCase();

        for (String skill : KNOWN_SKILLS) {

            if (text.contains(skill)) {

                JobSkill jobSkill =
                        JobSkill.builder()
                                .job(job)
                                .skillName(skill)
                                .build();

                jobSkillRepository.save(jobSkill);
            }
        }
    }
}