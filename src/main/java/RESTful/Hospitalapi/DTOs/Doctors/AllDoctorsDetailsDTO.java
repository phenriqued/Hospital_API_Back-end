package RESTful.Hospitalapi.DTOs.Doctors;

import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import RESTful.Hospitalapi.Entities.Doctors.Speciality.Speciality;

public record AllDoctorsDetailsDTO(InformationClientDTO information,String crm ,Speciality speciality) {

    public AllDoctorsDetailsDTO(DoctorEntity entity){
        this(new InformationClientDTO(entity.getInformation()), entity.getCrm(), entity.getSpeciality());
    }

}
