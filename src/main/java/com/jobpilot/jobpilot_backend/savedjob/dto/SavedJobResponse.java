package com.jobpilot.jobpilot_backend.savedjob.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SavedJobResponse {

    private Long savedJobId;

    private String title;

    private String company;

    private String location;
}