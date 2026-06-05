CREATE TABLE job_skills (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    job_id BIGINT NOT NULL,
    skill_name VARCHAR(255) NOT NULL,

    CONSTRAINT fk_job_skill
    FOREIGN KEY (job_id)
    REFERENCES jobs(id)
);