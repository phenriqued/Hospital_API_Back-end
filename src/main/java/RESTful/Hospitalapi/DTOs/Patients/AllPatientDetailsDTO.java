package RESTful.Hospitalapi.DTOs.Patients;

import RESTful.Hospitalapi.Entities.Patients.PatientEntity;

public record AllPatientDetailsDTO(String name, String cpf, String email, String phone) {

    public AllPatientDetailsDTO(PatientEntity entity){
        this(entity.getInformation().getName(), entity.getInformation().getCPF(), entity.getInformation().getEmail(),
                entity.getInformation().getPhone());
    }

}
