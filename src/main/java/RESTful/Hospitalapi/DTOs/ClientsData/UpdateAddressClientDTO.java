package RESTful.Hospitalapi.DTOs.ClientsData;

import jakarta.validation.constraints.Pattern;

public record UpdateAddressClientDTO(
        String nameStreet,
        @Pattern(regexp = "^[0-9]+$")
        String number,
        String complement,
        String district,
        String city,
        @Pattern(regexp = "^.{2}$")
        String uf,
        @Pattern(regexp = "\\d{8}")
        String cep) {
}
