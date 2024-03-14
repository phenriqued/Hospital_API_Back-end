package RESTful.Hospitalapi.Services.Doctors;

import RESTful.Hospitalapi.DTOs.Doctors.AllDoctorsDetailsDTO;
import RESTful.Hospitalapi.DTOs.Doctors.DoctorDetailsDTO;
import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.DTOs.Doctors.UpdateDoctorDTO;
import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import RESTful.Hospitalapi.Repositories.Doctors.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public DoctorEntity saveDoctor(RegisterDoctorDTO dto){
        var entity = new DoctorEntity(dto);
        return repository.save(entity);
    }

    public List<AllDoctorsDetailsDTO> listAllDoctors(Pageable pageable){
        return
                repository.findByIsActiveTrue(pageable).stream().map(AllDoctorsDetailsDTO::new).toList();
    }
    public DoctorDetailsDTO doctorDetails(Long id){
        return repository.findById(id).map(DoctorDetailsDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public void updateDoctor(Long id, UpdateDoctorDTO updateDoctor){
        var entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        entity.updateDoctor(updateDoctor);
        repository.flush();
    }

    public void deleteDoctor(Long id){
        var entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        entity.updateIsActive();
        repository.flush();
    }



}
