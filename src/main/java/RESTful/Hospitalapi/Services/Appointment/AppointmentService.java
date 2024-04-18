package RESTful.Hospitalapi.Services.Appointment;


import RESTful.Hospitalapi.DTOs.Doctors.AllDoctorsDetailsDTO;
import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentDTO;
import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentSchedulingDTO;
import RESTful.Hospitalapi.DTOs.MedicalAppointment.CancelAppointmentDTO;
import RESTful.Hospitalapi.DTOs.Patients.AllPatientDetailsDTO;
import RESTful.Hospitalapi.Entities.MedicalAppointment.AppointmentEntity;
import RESTful.Hospitalapi.Repositories.MedicalAppointments.AppointmentRepository;
import RESTful.Hospitalapi.Repositories.Patient.PatientRepository;
import RESTful.Hospitalapi.Services.Appointment.Validations.CancelScheduleValidations.CancelScheduleValidation;
import RESTful.Hospitalapi.Services.Appointment.Validations.SchedulingValidation.SchedulingValidations;
import RESTful.Hospitalapi.Services.Appointment.Validations.SchedulingValidation.ValidationDoctorAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ValidationDoctorAvailability doctorAvailability;
    @Autowired
    private List<SchedulingValidations> schedulingValidations;
    @Autowired
    private List<CancelScheduleValidation> cancelValidations;

    public AppointmentSchedulingDTO scheduleAppointment(AppointmentDTO appointment){
        schedulingValidations.forEach(schedulingValidations -> schedulingValidations.validation(appointment));

        var patient = patientRepository.findByIdAndIsActiveTrue(appointment.patientId()).orElseThrow();
        var doctor = doctorAvailability.findAvailableDoctor(appointment);

        var entity = new AppointmentEntity(null, patient, doctor, appointment.dateTime(), appointment.endDateTime());
        appointmentRepository.save(entity);

        return new AppointmentSchedulingDTO(new AllPatientDetailsDTO(patient), new AllDoctorsDetailsDTO(doctor), appointment.dateTime(), appointment.endDateTime());
    }

    public void cancelAppointment(CancelAppointmentDTO cancelAppointmentDTO){
        cancelValidations.forEach(cancel -> cancel.validation(cancelAppointmentDTO));
        appointmentRepository.deleteById(cancelAppointmentDTO.id());
    }


}
