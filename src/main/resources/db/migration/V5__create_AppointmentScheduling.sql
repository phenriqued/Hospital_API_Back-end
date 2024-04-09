CREATE TABLE tb_appointment
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    date_Time datetime NOT NULL,

    CONSTRAINT fk_appointments_patient_id FOREIGN KEY (patient_id) REFERENCES tb_patient (id),
    CONSTRAINT fk_appointments_doctor_id FOREIGN KEY (doctor_id) REFERENCES tb_doctor (id)

);