package RESTful.Hospitalapi.DTOs.MedicalAppointment;

import RESTful.Hospitalapi.DTOs.Doctors.AllDoctorsDetailsDTO;
import RESTful.Hospitalapi.DTOs.Patients.AllPatientDetailsDTO;

import java.time.LocalDateTime;

public record AppointmentSchedulingDTO(AllPatientDetailsDTO patient, AllDoctorsDetailsDTO doctor, LocalDateTime dateTime, LocalDateTime endDateTime) {
}
