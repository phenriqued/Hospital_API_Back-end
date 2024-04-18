package RESTful.Hospitalapi.Services.Appointment.Validations.CancelScheduleValidations;

import RESTful.Hospitalapi.DTOs.MedicalAppointment.CancelAppointmentDTO;
import RESTful.Hospitalapi.Infra.Exceptions.ValidationException;

public class CheckReasonValidation implements CancelScheduleValidation{
    @Override
    public void validation(CancelAppointmentDTO cancelAppointmentDTO) {
        if(cancelAppointmentDTO.reason() == CancelAppointmentDTO.Reason.OTHERS
                && cancelAppointmentDTO.additionalReasonText() == null){
            throw new ValidationException("Please provide an additional reason when canceling your appointment.");
        }
    }
}
