package RESTful.Hospitalapi.DTOs.ClientsData;

import RESTful.Hospitalapi.Entities.ClientsData.InformationClient;
import br.com.caelum.stella.validation.CPFValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record InformationClientDTO(
        @NotBlank
        String cpf,
        @NotBlank
        String name,
        @Email
        String email,
        @Pattern(regexp = "^[0-9]{2}-?[0-9]{9}$")
        String phone) {

    public InformationClientDTO{
        if(!validateCpf(cpf))
            throw new IllegalArgumentException("Invalid CPF");

    }

    public InformationClientDTO(InformationClient information){
        this(information.getCpf(), information.getName(), information.getEmail(), information.getPhone());
    }


    private Boolean validateCpf(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
