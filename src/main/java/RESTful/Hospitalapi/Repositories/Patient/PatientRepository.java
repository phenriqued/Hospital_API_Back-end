package RESTful.Hospitalapi.Repositories.Patient;

import RESTful.Hospitalapi.Entities.Patients.PatientEntity;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Page<PatientEntity> findByIsActiveTrue(Pageable pageable);

    Optional<PatientEntity> findByIdAndIsActiveTrue(Long id);


}
