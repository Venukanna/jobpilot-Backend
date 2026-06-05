package com.jobpilot.jobpilot_backend.jobskill.entity;

import com.jobpilot.jobpilot_backend.job.entity.Job;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
}