package com.jobpilot.jobpilot_backend.jobfetch.dto.jsearch;

import lombok.Data;
import java.util.List;

@Data
public class JSearchResponse {

    private List<JSearchJobDTO> data;
}