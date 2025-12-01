CREATE TABLE feedbacks
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(100),
    email        VARCHAR(100),
    contact_type VARCHAR(50)   NOT NULL,
    message      VARCHAR(1000) NOT NULL,
    submitted_at TIMESTAMP     NOT NULL DEFAULT NOW()
);
