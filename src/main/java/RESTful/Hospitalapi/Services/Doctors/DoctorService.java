package RESTful.Hospitalapi.Services.Doctors;

import RESTful.Hospitalapi.DTOs.Doctors.AllDoctorsDetailsDTO;
import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import RESTful.Hospitalapi.Repositories.Doctors.DoctorRepository;
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

}
