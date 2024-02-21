package RESTful.Hospitalapi.Entities.ClientsData;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AddressClient {

    private String nameStreet;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String uf;
    private String cep;

    public AddressClient(AddressClientDTO dto){
        this(dto.nameStreet(), dto.number(), dto.complement(), dto.district(), dto.city(),
                dto.uf(), dto.cep());
    }


}
