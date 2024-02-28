package RESTful.Hospitalapi.Controllers.Doctor;


import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.Services.Doctors.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/Doctor")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public ResponseEntity createDoctor(@RequestBody @Valid RegisterDoctorDTO registerDoctor, UriComponentsBuilder uriComponentsBuilder){
        var doctor = service.saveDoctor(registerDoctor);
        URI uri = uriComponentsBuilder.path("/Doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(doctor);
    }



}
