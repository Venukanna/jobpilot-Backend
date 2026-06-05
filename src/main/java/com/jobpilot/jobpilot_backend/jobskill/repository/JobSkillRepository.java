package com.jobpilot.jobpilot_backend.jobskill.repository;

import com.jobpilot.jobpilot_backend.jobskill.entity.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSkillRepository
        extends JpaRepository<JobSkill, Long> {

    List<JobSkill> findByJobId(Long jobId);
}