package RESTful.Hospitalapi.Entities.ClientsData;

import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InformationClient {

    private String CPF;
    private String name;
    private String email;
    private String phone;

    public InformationClient(InformationClientDTO information){
        this(information.cpf(), information.name(), information.email(), information.phone());
    }

}
