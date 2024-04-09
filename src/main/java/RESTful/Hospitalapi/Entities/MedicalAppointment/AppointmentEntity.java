package RESTful.Hospitalapi.Entities.MedicalAppointment;


import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_appointment")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "patient_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PatientEntity patient;

    @JoinColumn(name = "doctor_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DoctorEntity doctor;

    private LocalDateTime dateTime;

}
