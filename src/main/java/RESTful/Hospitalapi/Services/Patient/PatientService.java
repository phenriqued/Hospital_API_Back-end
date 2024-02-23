package RESTful.Hospitalapi.Services.Patient;


import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;
import RESTful.Hospitalapi.Repositories.Patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;


    public PatientEntity createPatient(RegisterPatientDTO dto){
        PatientEntity entity = new PatientEntity(dto);
        return repository.save(entity);
    }

}
