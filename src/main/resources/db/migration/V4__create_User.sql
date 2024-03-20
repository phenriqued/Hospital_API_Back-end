CREATE TABLE tb_user
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_Name            VARCHAR(255) UNIQUE,
    password             VARCHAR(255)
);