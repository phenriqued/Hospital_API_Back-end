package RESTful.Hospitalapi.Services.Appointment.Validations.SchedulingValidation;


import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentDTO;
import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import RESTful.Hospitalapi.Infra.Exceptions.ValidationException;
import RESTful.Hospitalapi.Repositories.Doctors.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationDoctorAvailability {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ValidationDoctorScheduling doctorScheduling;

    public DoctorEntity findAvailableDoctor(AppointmentDTO appointment){
        if(appointment.doctorId() == null && appointment.speciality() == null){
            throw new ValidationException("It is not possible to make an appointment without a doctor");
        }
        var doctor = getAvailableDoctor(appointment);
        if(doctor != null){
            return doctor;
        }else{
            throw new ValidationException("[ERROR] Unable to locate doctor!");
        }
    }

    private DoctorEntity getAvailableDoctor(AppointmentDTO appointment){
        if(appointment.doctorId() != null){
            return doctorRepository.findByIdAndIsActiveTrue(appointment.doctorId()).orElse(null);
        }else{
            return doctorRepository.findAllBySpecialityAndIsActiveTrue(appointment.speciality()).stream()
                    .filter(doc -> !(doctorScheduling.checkDoctorScheduling(doc.getId(), appointment.dateTime())) )
                    .findFirst().orElse(null);
        }
    }

}
