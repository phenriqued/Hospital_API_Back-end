package RESTful.Hospitalapi.DTOs.MedicalAppointment;

import RESTful.Hospitalapi.Entities.Doctors.Speciality.Speciality;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDTO(
        @NotNull
        @JsonAlias("patient")
        Long patientId,
        @JsonAlias("doctor")
        Long doctorId,
        @NotNull
        @Future
        LocalDateTime dateTime) {


}
