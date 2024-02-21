package RESTful.Hospitalapi.Entities.Patients;


import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.Entities.ClientsData.AddressClient;
import RESTful.Hospitalapi.Entities.ClientsData.InformationClient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_patient")

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private InformationClient information;
    @Embedded
    private AddressClient address;
    private Boolean isActive;

    public PatientEntity(RegisterPatientDTO register){
        this.information = new InformationClient(register.information());
        this.address = new AddressClient(register.address());
        this.isActive = true;
    }


    public void disable(){
        this.isActive = false;
    }

}
