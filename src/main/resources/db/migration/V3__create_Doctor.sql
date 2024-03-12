CREATE TABLE tb_doctor
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    crm            VARCHAR(10) UNIQUE,
    information_id BIGINT,
    address_id     BIGINT,
    speciality     VARCHAR(100),
    is_Active      BOOLEAN,

    FOREIGN KEY (information_id) REFERENCES tb_information_client (id),
    FOREIGN KEY (address_id) REFERENCES tb_address_client (id)

);