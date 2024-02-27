package RESTful.Hospitalapi.Repositories.Doctors;

import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Page<DoctorEntity> findByIsActiveTrue(Pageable pageable);

}
