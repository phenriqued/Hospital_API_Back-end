package RESTful.Hospitalapi.Repositories.MedicalAppointments;

import RESTful.Hospitalapi.Entities.MedicalAppointment.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    Optional<AppointmentEntity> findByPatientIdAndDateTime(Long id, LocalDateTime dateTime);

    List<AppointmentEntity> findByDoctorId(Long id);
}
