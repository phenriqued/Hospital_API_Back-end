package RESTful.Hospitalapi.DTOs.ClientsData;

public record AddressClientDTO(
        String nameStreet,
        String number,
        String complement,
        String district,
        String city,
        String uf,
        String cep) {

}
