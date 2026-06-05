package com.jobpilot.jobpilot_backend.skill.controller;

import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import com.jobpilot.jobpilot_backend.skill.dto.SkillResponse;
import com.jobpilot.jobpilot_backend.skill.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ApiResponse<List<SkillResponse>> getSkills() {

        return ApiResponse.<List<SkillResponse>>builder()
                .success(true)
                .message("Skills fetched successfully")
                .data(skillService.getUserSkills())
                .build();
    }
}