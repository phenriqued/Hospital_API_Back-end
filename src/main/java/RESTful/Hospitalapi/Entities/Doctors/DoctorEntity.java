package RESTful.Hospitalapi.Entities.Doctors;


import RESTful.Hospitalapi.DTOs.ClientsData.UpdateInformationClientDTO;
import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.DTOs.Doctors.UpdateDoctorDTO;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "information_id")
    private InformationClient information;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressClient address;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    private Boolean isActive;


    public DoctorEntity(RegisterDoctorDTO dto){
        this.information = new InformationClient(dto.information());
        this.address = new AddressClient(dto.address());
        this.speciality = Speciality.typeOfDoctor(dto.speciality());
        this.crm = createCrm();
        this.isActive = true;
    }


    public void updateIsActive(){
        this.isActive = !isActive;
    }

    public void updateDoctor(UpdateDoctorDTO update){
        information.updateInformationClient(update.information());
        address.updateAddressClient(update.address());
        updateDoctorSpeciality(update.speciality());
    }


    private String createCrm(){
        StringBuilder value = new StringBuilder(speciality.getValue() + "-");
        for (int i=0; i<5; i++){
            value.append((int) (Math.random() * 10));
        }
        return value.toString();
    }

    private void updateDoctorSpeciality(String speciality){
        if (speciality != null){
            this.speciality = Speciality.typeOfDoctor(speciality);
            this.crm = createCrm();
        }
    }


}
