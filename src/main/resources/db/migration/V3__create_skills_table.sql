CREATE TABLE skills (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    skill_name VARCHAR(255) NOT NULL,

    CONSTRAINT fk_skill_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
);