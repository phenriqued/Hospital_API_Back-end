package RESTful.Hospitalapi.Services.Appointment.Validations.SchedulingValidation;

import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentDTO;
import RESTful.Hospitalapi.Infra.Exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidationOfDateTime implements SchedulingValidations{

    @Override
    public void validation(AppointmentDTO data) {
        var dateTime = data.dateTime();
        if(dateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                || dateTime.getHour() < 7 || dateTime.getHour()>18){
            throw new ValidationException("The date and/or time is outside opening hours");
        }
        if(dateTime.isBefore(LocalDateTime.now().plusMinutes(30))){
            throw new ValidationException("Medical appointments must be scheduled 30 minutes in advance");
        }
    }
}
