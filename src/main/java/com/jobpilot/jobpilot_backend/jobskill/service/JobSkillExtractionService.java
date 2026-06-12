package com.jobpilot.jobpilot_backend.jobskill.service;

import com.jobpilot.jobpilot_backend.job.entity.Job;

public interface JobSkillExtractionService {

    void extractAndSave(Job job);
}