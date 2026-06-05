package com.jobpilot.jobpilot_backend.application.service;

import com.jobpilot.jobpilot_backend.application.dto.ApplicationResponse;

import java.util.List;

public interface ApplicationService {

    void applyJob(Long jobId);

    List<ApplicationResponse> getMyApplications();
}