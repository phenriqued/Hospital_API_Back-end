CREATE TABLE tb_address_client
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name_street VARCHAR(255),
    number      VARCHAR(10),
    complement  VARCHAR(255),
    district    VARCHAR(255),
    city        VARCHAR(255),
    uf          VARCHAR(4),
    cep         VARCHAR(10)
);

CREATE TABLE tb_information_client
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    cpf   VARCHAR(11) UNIQUE,
    name  VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(255)
);