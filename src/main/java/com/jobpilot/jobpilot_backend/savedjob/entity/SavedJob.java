package com.jobpilot.jobpilot_backend.savedjob.entity;

import com.jobpilot.jobpilot_backend.job.entity.Job;
import com.jobpilot.jobpilot_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "saved_jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private LocalDateTime savedAt;
}