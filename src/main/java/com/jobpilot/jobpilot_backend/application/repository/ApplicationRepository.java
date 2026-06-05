package com.jobpilot.jobpilot_backend.application.repository;

import com.jobpilot.jobpilot_backend.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    List<Application> findByUserId(Long userId);

    boolean existsByUserIdAndJobId(
            Long userId,
            Long jobId
    );
}