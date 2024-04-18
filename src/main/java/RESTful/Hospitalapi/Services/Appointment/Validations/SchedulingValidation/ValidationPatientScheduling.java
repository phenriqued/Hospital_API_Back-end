package RESTful.Hospitalapi.Services.Appointment.Validations.SchedulingValidation;

import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentDTO;
import RESTful.Hospitalapi.Infra.Exceptions.ValidationException;
import RESTful.Hospitalapi.Repositories.MedicalAppointments.AppointmentRepository;
import RESTful.Hospitalapi.Repositories.Patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidationPatientScheduling implements SchedulingValidations{

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void validation(AppointmentDTO data) {
        if (patientRepository.findByIdAndIsActiveTrue(data.patientId()).isEmpty()){
            throw new ValidationException("User Not Found");
        }
        if(repository.findByPatientIdAndDateTime(data.patientId(), data.dateTime()).isPresent()){
            throw new ValidationException("The patient cannot have two appointments on the same day");
        }

    }
}
