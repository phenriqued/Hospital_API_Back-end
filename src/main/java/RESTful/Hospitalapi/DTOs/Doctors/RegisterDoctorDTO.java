package RESTful.Hospitalapi.DTOs.Doctors;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record RegisterDoctorDTO(
        @Valid
        InformationClientDTO information,
        @Valid
        AddressClientDTO address,
        @NotBlank
        String speciality) {

}
