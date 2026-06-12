package com.jobpilot.jobpilot_backend.jobfetch.client;

import com.jobpilot.jobpilot_backend.jobfetch.dto.JobSourceDTO;
import com.jobpilot.jobpilot_backend.jobfetch.dto.jsearch.JSearchJobDTO;
import com.jobpilot.jobpilot_backend.jobfetch.dto.jsearch.JSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JSearchClient {

    private final RestTemplate restTemplate;

    @Value("${rapidapi.key}")
    private String apiKey;

    @Value("${rapidapi.host}")
    private String apiHost;

    public List<JobSourceDTO> searchJobs() {

        String url =
                "https://jsearch.p.rapidapi.com/search?query=Java Developer in India&page=1&num_pages=1";

        HttpHeaders headers = new HttpHeaders();

        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<String> entity =
                new HttpEntity<>(headers);

        ResponseEntity<JSearchResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        JSearchResponse.class
                );

        JSearchResponse body = response.getBody();

        if (body == null || body.getData() == null) {
            return List.of();
        }
        System.out.println("Jobs found from API: " + body.getData().size());

        body.getData().stream().findFirst()
                .ifPresent(job ->
                        System.out.println("First Job: " + job.getJobTitle()));

        return body.getData()
                .stream()
                .map(this::mapToJobSourceDTO)
                .toList();
    }

    private JobSourceDTO mapToJobSourceDTO(
            JSearchJobDTO job) {

        return JobSourceDTO.builder()
                .title(job.getJobTitle())
                .company(job.getEmployerName())
                .location(job.getJobLocation())
                .description(job.getJobDescription())
                .jobUrl(job.getJobApplyLink())
                .source("JSEARCH")
                .build();
    }
}