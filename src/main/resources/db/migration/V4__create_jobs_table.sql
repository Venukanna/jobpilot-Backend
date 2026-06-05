CREATE TABLE jobs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    title VARCHAR(255) NOT NULL,

    company VARCHAR(255) NOT NULL,

    location VARCHAR(255),

    experience_required VARCHAR(100),

    job_url VARCHAR(1000),

    description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);