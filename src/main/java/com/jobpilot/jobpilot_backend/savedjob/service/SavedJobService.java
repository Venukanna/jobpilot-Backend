package com.jobpilot.jobpilot_backend.savedjob.service;

import com.jobpilot.jobpilot_backend.savedjob.dto.SavedJobResponse;

import java.util.List;

public interface SavedJobService {

    void saveJob(Long jobId);

    void removeSavedJob(Long jobId);

    List<SavedJobResponse> getSavedJobs();
}