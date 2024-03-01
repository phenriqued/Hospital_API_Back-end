package RESTful.Hospitalapi.DTOs.Doctors;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;

public record DoctorDetailsDTO(InformationClientDTO information, String speciality,
                                         String crm,AddressClientDTO address, Boolean isActive) {

    public DoctorDetailsDTO(DoctorEntity entity){
        this(new InformationClientDTO(entity.getInformation()), entity.getSpeciality().name(),
                entity.getCrm(), new AddressClientDTO(entity.getAddress()), entity.getIsActive());
    }
}
