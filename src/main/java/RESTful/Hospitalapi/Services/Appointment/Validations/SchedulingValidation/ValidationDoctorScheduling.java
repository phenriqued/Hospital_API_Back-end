package RESTful.Hospitalapi.Services.Appointment.Validations.SchedulingValidation;

import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentDTO;
import RESTful.Hospitalapi.Infra.Exceptions.ValidationException;
import RESTful.Hospitalapi.Repositories.Doctors.DoctorRepository;
import RESTful.Hospitalapi.Repositories.MedicalAppointments.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidationDoctorScheduling implements SchedulingValidations{

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void validation(AppointmentDTO data) {
        boolean checkScheduling;
        if(data.doctorId() == null){
            checkScheduling =  doctorRepository.findAllBySpecialityAndIsActiveTrue(data.speciality()).stream()
                    .anyMatch(doc -> checkDoctorScheduling(doc.getId(), data.dateTime()));
            if(checkScheduling)
                throw new ValidationException("There is no doctor available for the requested specialty");

        }else{
            if(doctorRepository.findByIdAndIsActiveTrue(data.doctorId()).isEmpty()){
                throw new ValidationException("Doctor not found");
            }
            checkScheduling = checkDoctorScheduling(data.doctorId(), data.dateTime());
        }
        if(checkScheduling)
            throw new ValidationException("There is already an appointment for this doctor at this date and time.");
    }

    protected Boolean checkDoctorScheduling(Long id, LocalDateTime dateTime){
        return repository.findByDoctorIdAndDate(id, dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()).stream()
                .anyMatch(scheduling -> isTimeConflict(scheduling.getDateTime(), dateTime));
    }
    private boolean isTimeConflict(LocalDateTime checkDateTime, LocalDateTime dateTime){
        var diffMinutes = Duration.between(checkDateTime, dateTime).toMinutes();
        return  diffMinutes >= 0 && diffMinutes < 60;
    }
}
