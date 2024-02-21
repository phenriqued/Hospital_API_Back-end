package RESTful.Hospitalapi.Controllers.Patient;


import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.Services.Patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Patient")
public class PatientController {

    @Autowired
    private PatientService service;


    @PostMapping
    public ResponseEntity registerPatient(@RequestBody RegisterPatientDTO dto){
        service.createPatient(dto);
        return ResponseEntity.ok().body("Created!");
    }


}
