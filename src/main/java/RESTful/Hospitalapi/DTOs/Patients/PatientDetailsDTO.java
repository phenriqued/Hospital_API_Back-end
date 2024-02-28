package RESTful.Hospitalapi.DTOs.Patients;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;

public record PatientDetailsDTO(InformationClientDTO information, AddressClientDTO address, Boolean isActive) {

    public PatientDetailsDTO(PatientEntity entity){
        this( new InformationClientDTO(entity.getInformation()),
               new AddressClientDTO(entity.getAddress()), entity.getIsActive());
    }

}
