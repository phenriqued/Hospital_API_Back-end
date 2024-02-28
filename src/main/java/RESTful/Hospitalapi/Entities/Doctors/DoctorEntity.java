package RESTful.Hospitalapi.Entities.Doctors;


import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.Entities.ClientsData.AddressClient;
import RESTful.Hospitalapi.Entities.ClientsData.InformationClient;
import RESTful.Hospitalapi.Entities.Doctors.Speciality.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_doctor")

@AllArgsConstructor @NoArgsConstructor
@Getter
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String crm;
    @Embedded
    private InformationClient information;
    @Embedded
    private AddressClient address;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    private Boolean isActive;


    public DoctorEntity(RegisterDoctorDTO dto){
        this.information = new InformationClient(dto.information());
        this.address = new AddressClient(dto.address());
        this.speciality = typeOfDoctor(dto.speciality());
        this.crm = createCrm();
        this.isActive = true;
    }



    public void disable(){
        this.isActive = false;
    }

    private String createCrm(){
        StringBuilder value = new StringBuilder(speciality.getValue() + "-");
        for (int i=0; i<5; i++){
            value.append((int) (Math.random() * 10));
        }
        return value.toString();
    }

    private Speciality typeOfDoctor(String value){
        return Speciality.valueOf(value.toUpperCase());
    }
    private Speciality typeOfDoctor(Integer value){
        return Speciality.intValueOf(value);
    }


}
