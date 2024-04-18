package RESTful.Hospitalapi.Services.Appointment.Validations.CancelScheduleValidations;

import RESTful.Hospitalapi.DTOs.MedicalAppointment.CancelAppointmentDTO;

public interface CancelScheduleValidation {
    void validation(CancelAppointmentDTO cancelAppointmentDTO);

}
