package RESTful.Hospitalapi.DTOs.Patients;

import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;

public record AllPatientDetailsDTO(InformationClientDTO informationClient) {

    public AllPatientDetailsDTO(PatientEntity entity){
        this(new InformationClientDTO(entity.getInformation()));
    }

}
