package RESTful.Hospitalapi.Entities.Doctors;


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




    public void disable(){
        this.isActive = false;
    }

    private String createCrm(){
        StringBuilder value = new StringBuilder(speciality.getValue());
        for (int i=0; i<5; i++){
            value.append((int) (Math.random() * 100));
        }
        return value.toString();
    }

}
