package RESTful.Hospitalapi.Services.Doctors;

import RESTful.Hospitalapi.Repositories.Doctors.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;


}
