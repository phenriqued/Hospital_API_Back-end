package RESTful.Hospitalapi.Repositories.Doctors;

import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import RESTful.Hospitalapi.Entities.Doctors.Speciality.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Page<DoctorEntity> findByIsActiveTrue(Pageable pageable);

    Optional<DoctorEntity> findByIdAndIsActiveTrue(Long id);

    List<DoctorEntity> findAllBySpecialityAndIsActiveTrue(Speciality speciality);

}
