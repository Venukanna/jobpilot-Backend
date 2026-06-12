package com.jobpilot.jobpilot_backend.jobfetch.dto.jsearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JSearchJobDTO {

    @JsonProperty("job_id")
    private String jobId;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("employer_name")
    private String employerName;

    @JsonProperty("job_location")
    private String jobLocation;

    @JsonProperty("job_description")
    private String jobDescription;

    @JsonProperty("job_apply_link")
    private String jobApplyLink;
}