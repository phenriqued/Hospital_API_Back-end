package RESTful.Hospitalapi.Entities.Patients;


import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.DTOs.Patients.UpdatePatientDTO;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "information_id")
    private InformationClient information;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressClient address;
    private Boolean isActive;

    public PatientEntity(RegisterPatientDTO register) {
        this.information = new InformationClient(register.information());
        this.address = new AddressClient(register.address());
        this.isActive = true;
    }

    public void updatePatient(UpdatePatientDTO updatePatient) {
        information.updateInformationClient(updatePatient.information());
        address.updateAddressClient(updatePatient.address());
    }


    public void disable() {
        this.isActive = false;
    }

}
