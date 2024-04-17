package RESTful.Hospitalapi.Services.Appointment;


import RESTful.Hospitalapi.DTOs.Doctors.AllDoctorsDetailsDTO;
import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentDTO;
import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentSchedulingDTO;
import RESTful.Hospitalapi.DTOs.MedicalAppointment.CancelAppointmentDTO;
import RESTful.Hospitalapi.DTOs.Patients.AllPatientDetailsDTO;
import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import RESTful.Hospitalapi.Entities.Doctors.Speciality.Speciality;
import RESTful.Hospitalapi.Entities.MedicalAppointment.AppointmentEntity;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;
import RESTful.Hospitalapi.Repositories.Doctors.DoctorRepository;
import RESTful.Hospitalapi.Repositories.MedicalAppointments.AppointmentRepository;
import RESTful.Hospitalapi.Repositories.Patient.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public AppointmentSchedulingDTO scheduleAppointment(AppointmentDTO appointment){

        validationOfDateTime(appointment.dateTime());
        var patient = validationPatientScheduling(appointment.patientId(), appointment.dateTime());
        var doctor = doctorValidation(appointment.doctorId(), appointment.speciality(), appointment.dateTime());

        var entity = new AppointmentEntity(null,patient, doctor, appointment.dateTime(), appointment.endDateTime());
        appointmentRepository.save(entity);

        return  new AppointmentSchedulingDTO(new AllPatientDetailsDTO(patient), new AllDoctorsDetailsDTO(doctor), appointment.dateTime(), appointment.endDateTime());
    }

    public void cancelAppointment(CancelAppointmentDTO cancelAppointmentDTO){
        if(cancelAppointmentDTO.reason() == CancelAppointmentDTO.Reason.OTHERS && cancelAppointmentDTO.additionalReasonText() == null){
            throw new IllegalArgumentException("Please provide an additional reason when canceling your appointment.");
        }

        var cancel = appointmentRepository.findById(cancelAppointmentDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("The doctor's appointment cannot be found!"));

        if(LocalDateTime.now().minusHours(24).isBefore(cancel.getDateTime())){
            throw new IllegalArgumentException("It's only possible to cancel an appointment with 24 hours' notice.");
        }
        appointmentRepository.deleteById(cancel.getId());
    }


    private void validationOfDateTime(LocalDateTime dateTime) {
        if(dateTime.getHour() < 7 || dateTime.getHour() > 19 ||
                dateTime.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0 ||
                    dateTime.isBefore(LocalDateTime.now().plusMinutes(30))){
            throw new DateTimeException("The date and/or time is outside opening hours");
        }
    }
    private PatientEntity validationPatientScheduling(Long id, LocalDateTime dateTime){
        var patient = patientRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if(appointmentRepository.findByPatientIdAndDateTime(patient.getId(), dateTime).isPresent()){
            throw new IllegalArgumentException("The patient cannot have two appointments on the same day");
        }

        return patient;
    }

    private DoctorEntity doctorValidation(Long id, Speciality speciality, LocalDateTime startDateTime){
        if(id != null){
            var doctor = doctorRepository.findByIdAndIsActiveTrue(id)
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
            if(doctor.getSpeciality() != speciality ){
                throw new IllegalArgumentException("The specialty of the informed doctor is "+ doctor.getSpeciality()
                        +" not " + speciality);
            }
            if(checkDoctorSchedule(doctor.getId(), startDateTime)) {
                throw new IllegalArgumentException("There is already an appointment for this doctor at this date and time.");
            }
            return doctor;
        }else{
             return doctorRepository.findAllBySpecialityAndIsActiveTrue(speciality).stream().filter(
                    doc -> !(checkDoctorSchedule(doc.getId(), startDateTime)))
                    .findFirst().orElseThrow(
                    () -> new EntityNotFoundException("There is no doctor available for the requested specialty"));
        }
    }
    private boolean checkDoctorSchedule(Long id, LocalDateTime dateTime){
        return appointmentRepository.findByDoctorId(id).stream()
                .map(appointment -> isTimeConflict(appointment.getDateTime(), dateTime))
                .findFirst().orElse(false);
    }
    private boolean isTimeConflict(LocalDateTime checkDateTime, LocalDateTime dateTime){
        var diff = Duration.between(checkDateTime, dateTime);
        var diffMinutes = diff.toMinutes();
        return diffMinutes >= 0 && diffMinutes < 60;
    }




}
