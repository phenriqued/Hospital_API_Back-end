package RESTful.Hospitalapi.Entities.ClientsData;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.UpdateAddressClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.function.Consumer;

@Table(name = "tb_address_client")
@Entity

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddressClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameStreet;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String uf;
    private String cep;

    public AddressClient(AddressClientDTO dto){
        this(null, dto.nameStreet(), dto.number(), dto.complement(), dto.district(), dto.city(),
                dto.uf(), dto.cep());
    }

    public void updateAddressClient(UpdateAddressClientDTO update){
        if(update != null) {
            setIfNotEmpty(update.nameStreet(), this::setNameStreet);
            setIfNotEmpty(update.number(), this::setNumber);
            setIfNotEmpty(update.complement(), this::setComplement);
            setIfNotEmpty(update.district(), this::setDistrict);
            setIfNotEmpty(update.city(), this::setCity);
            setIfNotEmpty(update.uf(), this::setUf);
            setIfNotEmpty(update.cep(), this::setCep);
        }
    }

    private void setIfNotEmpty(String value, Consumer<String> setter){
        if(value != null && !value.isEmpty())
            setter.accept(value);
    }

}
