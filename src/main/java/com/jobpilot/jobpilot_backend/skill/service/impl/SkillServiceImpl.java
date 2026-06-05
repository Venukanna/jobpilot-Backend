package com.jobpilot.jobpilot_backend.skill.service.impl;

import com.jobpilot.jobpilot_backend.skill.entity.Skill;
import com.jobpilot.jobpilot_backend.skill.repository.SkillRepository;
import com.jobpilot.jobpilot_backend.skill.service.SkillService;
import com.jobpilot.jobpilot_backend.user.entity.User;
import com.jobpilot.jobpilot_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.jobpilot.jobpilot_backend.skill.dto.SkillResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    @Override
    public void saveSkills(Long userId,
                           List<String> skills) {

        skillRepository.deleteByUserId(userId);

        User user = userRepository.findById(userId)
                .orElseThrow();

        for (String skill : skills) {

            Skill newSkill = Skill.builder()
                    .skillName(skill)
                    .user(user)
                    .build();

            skillRepository.save(newSkill);
        }
    }

    @Override
    public List<SkillResponse> getUserSkills() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return skillRepository.findByUserId(user.getId())
                .stream()
                .map(skill ->
                        SkillResponse.builder()
                                .id(skill.getId())
                                .skillName(skill.getSkillName())
                                .build()
                )
                .toList();
    }
}