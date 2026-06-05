package com.jobpilot.jobpilot_backend.job.service.impl;

import com.jobpilot.jobpilot_backend.job.dto.JobResponse;
import com.jobpilot.jobpilot_backend.job.repository.JobRepository;
import com.jobpilot.jobpilot_backend.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.jobpilot.jobpilot_backend.skill.entity.Skill;
import com.jobpilot.jobpilot_backend.skill.repository.SkillRepository;
import com.jobpilot.jobpilot_backend.user.entity.User;
import com.jobpilot.jobpilot_backend.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.jobpilot.jobpilot_backend.job.dto.JobMatchResponse;
import com.jobpilot.jobpilot_backend.jobskill.entity.JobSkill;
import com.jobpilot.jobpilot_backend.jobskill.repository.JobSkillRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final JobSkillRepository jobSkillRepository;



    @Override
    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(job ->
                        JobResponse.builder()
                                .id(job.getId())
                                .title(job.getTitle())
                                .company(job.getCompany())
                                .location(job.getLocation())
                                .experienceRequired(
                                        job.getExperienceRequired()
                                )
                                .jobUrl(job.getJobUrl())
                                .build()
                )
                .toList();
    }
    @Override
    public List<JobMatchResponse> getMatchedJobs() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        List<String> userSkills =
                skillRepository.findByUserId(user.getId())
                        .stream()
                        .map(Skill::getSkillName)
                        .map(String::toLowerCase)
                        .toList();

        return jobRepository.findAll()
                .stream()
                .map(job -> {

                    List<String> jobSkills =
                            jobSkillRepository.findByJobId(job.getId())
                                    .stream()
                                    .map(JobSkill::getSkillName)
                                    .map(String::toLowerCase)
                                    .toList();

                    List<String> matchedSkills =
                            userSkills.stream()
                                    .filter(jobSkills::contains)
                                    .toList();

                    List<String> missingSkills =
                            jobSkills.stream()
                                    .filter(skill ->
                                            !userSkills.contains(skill))
                                    .toList();

                    int score =
                            userSkills.isEmpty()
                                    ? 0
                                    : (int) ((matchedSkills.size() * 100)
                                    / jobSkills.size());

                    return JobMatchResponse.builder()
                            .jobId(job.getId())
                            .title(job.getTitle())
                            .company(job.getCompany())
                            .matchScore(score)
                            .matchedSkills(matchedSkills)
                            .missingSkills(missingSkills)
                            .build();
                })
                .sorted((a, b) ->
                        b.getMatchScore()
                                .compareTo(a.getMatchScore()))
                .toList();
    }
}