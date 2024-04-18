package RESTful.Hospitalapi.Repositories.MedicalAppointments;

import RESTful.Hospitalapi.Entities.MedicalAppointment.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    Optional<AppointmentEntity> findByPatientIdAndDateTime(Long id, LocalDateTime dateTime);


    @Query("""
            SELECT appointment FROM AppointmentEntity appointment
             WHERE appointment.doctor.id = :id
             AND YEAR(appointment.dateTime) = :year
             AND MONTH(appointment.dateTime) = :month
             AND DAY(appointment.dateTime) = :day
            """)
    List<AppointmentEntity> findByDoctorIdAndDate(Long id, @Param("year") int year, @Param("month") int month, @Param("day") int day);
}
