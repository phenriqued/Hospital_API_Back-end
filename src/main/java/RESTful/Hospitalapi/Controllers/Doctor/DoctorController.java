package RESTful.Hospitalapi.Controllers.Doctor;


import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.Services.Doctors.DoctorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity listAllDoctor(@PageableDefault(size = 10, sort = "information.name")Pageable pageable){
        return ResponseEntity.ok().body(service.listAllDoctors(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity doctorDetails(@PathVariable Long id){
        if(!service.doctorIsExist(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(service.doctorDetails(id));
    }


}
