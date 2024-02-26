package RESTful.Hospitalapi.DTOs.Patients;

import RESTful.Hospitalapi.DTOs.ClientsData.UpdateAddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.UpdateInformationClientDTO;
import jakarta.validation.Valid;

public record UpdatePatientDTO(
        @Valid
        UpdateAddressClientDTO address,

        @Valid
        UpdateInformationClientDTO information) {


}
