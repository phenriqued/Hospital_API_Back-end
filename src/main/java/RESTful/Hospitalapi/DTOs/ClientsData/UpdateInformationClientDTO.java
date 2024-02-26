package RESTful.Hospitalapi.DTOs.ClientsData;

import jakarta.validation.constraints.Pattern;

public record UpdateInformationClientDTO (
        String name,
        String email,
        @Pattern(regexp = "^[0-9]{2}-?[0-9]{9}$")
        String phone) {

}
