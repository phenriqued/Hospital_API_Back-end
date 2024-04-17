package RESTful.Hospitalapi.DTOs.MedicalAppointment;

import jakarta.validation.constraints.NotNull;

public record CancelAppointmentDTO(
        @NotNull
        Long id,
        @NotNull
        Reason reason,

        String additionalReasonText) {

        public enum Reason{
                PATIENT_GAVE_UP("Patient gave up"),
                DOCTOR_CANCELED("Doctor canceled"),
                OTHERS("Others");

                private final String label;

                Reason(String label) {
                        this.label = label;
                }
                public String getLabel() {
                        return label;
                }
        }

}

