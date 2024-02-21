package RESTful.Hospitalapi.DTOs.ClientsData;

import br.com.caelum.stella.validation.CPFValidator;

public record InformationClientDTO(String cpf, String name, String email, String phone) {

    public InformationClientDTO{
        if(!validateCpf(cpf))
            throw new IllegalArgumentException("Invalid CPF");

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
