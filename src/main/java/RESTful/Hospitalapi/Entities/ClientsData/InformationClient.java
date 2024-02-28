package RESTful.Hospitalapi.Entities.ClientsData;

import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.UpdateInformationClientDTO;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.function.Consumer;

@NoArgsConstructor
@AllArgsConstructor
@Getter



@Embeddable
public class InformationClient {

    private String cpf;
    @Setter(AccessLevel.PRIVATE)
    private String name;
    @Setter(AccessLevel.PRIVATE)
    private String email;
    @Setter(AccessLevel.PRIVATE)
    private String phone;

    public InformationClient(InformationClientDTO information){
        this(information.cpf(), information.name(), information.email(), information.phone());
    }

    public void updateInformationClient(UpdateInformationClientDTO update){
        if(update != null) {
            setIfNotEmpty(update.name(), this::setName);
            setIfNotEmpty(update.email(), this::setEmail);
            setIfNotEmpty(update.phone(), this::setPhone);
        }
    }


    private void setIfNotEmpty(String value, Consumer<String> setter){
        if(value != null && !value.isEmpty())
            setter.accept(value);
    }

}
