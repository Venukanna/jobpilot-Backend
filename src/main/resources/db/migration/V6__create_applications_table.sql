CREATE TABLE applications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    user_id BIGINT NOT NULL,

    job_id BIGINT NOT NULL,

    status VARCHAR(50) NOT NULL,

    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_application_user
    FOREIGN KEY (user_id)
    REFERENCES users(id),

    CONSTRAINT fk_application_job
    FOREIGN KEY (job_id)
    REFERENCES jobs(id)
);