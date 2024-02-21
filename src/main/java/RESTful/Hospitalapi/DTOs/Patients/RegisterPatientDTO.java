package RESTful.Hospitalapi.DTOs.Patients;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;

public record RegisterPatientDTO(InformationClientDTO information, AddressClientDTO address) {
}
