package com.jobpilot.jobpilot_backend.resume.repository;

import com.jobpilot.jobpilot_backend.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByUserId(Long userId);
}