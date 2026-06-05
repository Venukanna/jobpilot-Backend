package com.jobpilot.jobpilot_backend.job.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String company;

    private String location;

    private String experienceRequired;

    private String jobUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime createdAt;
}