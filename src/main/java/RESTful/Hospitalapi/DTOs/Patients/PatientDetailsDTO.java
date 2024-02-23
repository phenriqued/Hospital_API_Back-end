package RESTful.Hospitalapi.DTOs.Patients;

import RESTful.Hospitalapi.Entities.ClientsData.AddressClient;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;

public record PatientDetailsDTO(String name, String cpf, String email, String phone) {

    public PatientDetailsDTO(PatientEntity entity){
        this(entity.getInformation().getName(), entity.getInformation().getCPF(), entity.getInformation().getEmail(),
                entity.getInformation().getPhone());
    }

}
