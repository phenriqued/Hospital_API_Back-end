package RESTful.Hospitalapi.Repositories.MedicalAppointments;

import RESTful.Hospitalapi.Entities.MedicalAppointment.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

}
