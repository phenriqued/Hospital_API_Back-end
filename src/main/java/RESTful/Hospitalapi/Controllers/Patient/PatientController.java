package RESTful.Hospitalapi.Controllers.Patient;


import RESTful.Hospitalapi.DTOs.Patients.AllPatientDetailsDTO;
import RESTful.Hospitalapi.DTOs.Patients.PatientDetailsDTO;
import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.DTOs.Patients.UpdatePatientDTO;
import RESTful.Hospitalapi.Services.Patient.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public ResponseEntity registerPatient(@RequestBody @Valid RegisterPatientDTO dto, UriComponentsBuilder uriBuilder){
        var patientCreated = service.createPatient(dto);

        URI uri = uriBuilder.path("/Patient/{id}").buildAndExpand(patientCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(patientCreated);
    }

    @GetMapping
    public ResponseEntity<List<AllPatientDetailsDTO>> listAllPatientDetails(@PageableDefault(size = 10, sort = "information.name") Pageable pageable){
        return ResponseEntity.ok().body(service.allPatientDetails(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailsDTO> patientDetails(@PathVariable Long id){
        if (service.patientDetails(id) != null){
            return ResponseEntity.ok().body(service.patientDetails(id));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientDTO updatePatient){
        if (!service.patientIsExist(id)){
            return ResponseEntity.badRequest().build();
        }
        service.updatePatient(id,updatePatient);
        return ResponseEntity.noContent().build();
    }


}
