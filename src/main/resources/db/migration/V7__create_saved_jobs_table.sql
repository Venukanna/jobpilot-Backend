CREATE TABLE saved_jobs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    user_id BIGINT NOT NULL,

    job_id BIGINT NOT NULL,

    saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_saved_user
    FOREIGN KEY (user_id)
    REFERENCES users(id),

    CONSTRAINT fk_saved_job
    FOREIGN KEY (job_id)
    REFERENCES jobs(id)
);