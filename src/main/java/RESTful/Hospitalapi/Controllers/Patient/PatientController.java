package RESTful.Hospitalapi.Controllers.Patient;


import RESTful.Hospitalapi.DTOs.Patients.AllPatientDetailsDTO;
import RESTful.Hospitalapi.DTOs.Patients.PatientDetailsDTO;
import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.DTOs.Patients.UpdatePatientDTO;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;
import RESTful.Hospitalapi.Services.Patient.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/Patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientEntity> registerPatient(@RequestBody @Valid RegisterPatientDTO dto, UriComponentsBuilder uriBuilder){
        var patientCreated = service.createPatient(dto);
        URI uri = uriBuilder.path("/Patient/{id}").buildAndExpand(patientCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(patientCreated);
    }

    @GetMapping
    public ResponseEntity<List<AllPatientDetailsDTO>> listAllPatient(@PageableDefault(size = 10, sort = "information.name") Pageable pageable){
        return ResponseEntity.ok().body(service.allPatientDetails(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailsDTO> patientDetails(@PathVariable Long id){
        return ResponseEntity.ok().body(service.patientDetails(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientDTO updatePatient) {
        service.updatePatient(id, updatePatient);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.ok().build();
    }

}
