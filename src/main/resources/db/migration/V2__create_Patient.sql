CREATE TABLE tb_patient
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    information_id BIGINT,
    address_id     BIGINT,
    is_Active      BOOLEAN,

    FOREIGN KEY (information_id) REFERENCES tb_information_client (id),
    FOREIGN KEY (address_id) REFERENCES tb_address_client (id)
);