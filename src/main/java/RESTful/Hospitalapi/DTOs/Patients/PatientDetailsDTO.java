package RESTful.Hospitalapi.DTOs.Patients;

import RESTful.Hospitalapi.Entities.ClientsData.AddressClient;
import RESTful.Hospitalapi.Entities.ClientsData.InformationClient;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;

public record PatientDetailsDTO(InformationClient information, AddressClient address, Boolean isActive) {

    public PatientDetailsDTO(PatientEntity entity){
        this(entity.getInformation(), entity.getAddress(), entity.getIsActive());
    }

}
