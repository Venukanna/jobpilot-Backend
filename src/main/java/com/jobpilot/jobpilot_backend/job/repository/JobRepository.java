package com.jobpilot.jobpilot_backend.job.repository;

import com.jobpilot.jobpilot_backend.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository
        extends JpaRepository<Job, Long> {
}