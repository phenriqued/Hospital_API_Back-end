package RESTful.Hospitalapi.DTOs.Patients;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RegisterPatientDTO(
        @NotNull
        @Valid InformationClientDTO information,
        @NotNull
        @Valid AddressClientDTO address) {
}
