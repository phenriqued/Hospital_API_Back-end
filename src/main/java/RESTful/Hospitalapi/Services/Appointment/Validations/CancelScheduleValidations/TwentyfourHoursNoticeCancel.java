package RESTful.Hospitalapi.Services.Appointment.Validations.CancelScheduleValidations;

import RESTful.Hospitalapi.DTOs.MedicalAppointment.CancelAppointmentDTO;
import RESTful.Hospitalapi.Infra.Exceptions.ValidationException;
import RESTful.Hospitalapi.Repositories.MedicalAppointments.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TwentyfourHoursNoticeCancel implements CancelScheduleValidation{

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validation(CancelAppointmentDTO cancelAppointmentDTO) {
        var cancel = repository.findById(cancelAppointmentDTO.id())
                .orElseThrow(() ->  new ValidationException("The doctor's appointment cannot be found!"));

        if(cancel.getDateTime().minusHours(24).isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("It's only possible to cancel an appointment with 24 hours' notice.");
        }
    }
}
