package com.jobpilot.jobpilot_backend.savedjob.repository;

import com.jobpilot.jobpilot_backend.savedjob.entity.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SavedJobRepository
        extends JpaRepository<SavedJob, Long> {

    List<SavedJob> findByUserId(Long userId);

    boolean existsByUserIdAndJobId(
            Long userId,
            Long jobId
    );

    @Transactional
    @Modifying
    void deleteByUserIdAndJobId(
            Long userId,
            Long jobId
    );
}