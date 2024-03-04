package RESTful.Hospitalapi.DTOs.Doctors;

import RESTful.Hospitalapi.DTOs.ClientsData.UpdateAddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.UpdateInformationClientDTO;
import jakarta.validation.Valid;

public record UpdateDoctorDTO(
        @Valid
        UpdateInformationClientDTO information,
        @Valid
        UpdateAddressClientDTO address,
        String speciality

) {
}
