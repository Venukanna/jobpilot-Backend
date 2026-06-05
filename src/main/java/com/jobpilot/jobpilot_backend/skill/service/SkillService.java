package com.jobpilot.jobpilot_backend.skill.service;
import com.jobpilot.jobpilot_backend.skill.dto.SkillResponse;
import java.util.List;

public interface SkillService {

    void saveSkills(Long userId, List<String> skills);
    List<SkillResponse> getUserSkills();
}