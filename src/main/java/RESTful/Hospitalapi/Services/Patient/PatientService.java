package RESTful.Hospitalapi.Services.Patient;


import RESTful.Hospitalapi.DTOs.Patients.AllPatientDetailsDTO;
import RESTful.Hospitalapi.DTOs.Patients.PatientDetailsDTO;
import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.DTOs.Patients.UpdatePatientDTO;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;
import RESTful.Hospitalapi.Repositories.Patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;


    public PatientEntity createPatient(RegisterPatientDTO dto){
        PatientEntity entity = new PatientEntity(dto);
        return repository.save(entity);
    }

    public List<AllPatientDetailsDTO> allPatientDetails(Pageable pageable){
        return repository.findByIsActiveTrue(pageable).map(AllPatientDetailsDTO::new).toList();
    }

    public PatientDetailsDTO patientDetails(Long id){
        return repository.findById(id).map(PatientDetailsDTO::new).orElse(null);
    }

    public void updatePatient(Long id, UpdatePatientDTO update){
        var entity = repository.findById(id).orElseThrow();
        entity.updatePatient(update);
        repository.flush();
    }

    public Boolean patientIsExist(Long id){
        return repository.findById(id).isPresent();
    }

    public void deletePatient(Long id){
        var entity = repository.findById(id).orElseThrow();
        entity.disable();
        repository.flush();
    }


}
