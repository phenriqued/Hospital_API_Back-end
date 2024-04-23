package RESTful.Hospitalapi.Controllers.Doctor;


import RESTful.Hospitalapi.DTOs.Doctors.AllDoctorsDetailsDTO;
import RESTful.Hospitalapi.DTOs.Doctors.DoctorDetailsDTO;
import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.DTOs.Doctors.UpdateDoctorDTO;
import RESTful.Hospitalapi.Services.Doctors.DoctorService;
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

@RestController
@RequestMapping("/Doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    @Transactional
    public ResponseEntity createDoctor(@RequestBody @Valid RegisterDoctorDTO registerDoctor, UriComponentsBuilder uriComponentsBuilder){
        var doctor = service.saveDoctor(registerDoctor);
        URI uri = uriComponentsBuilder.path("/Doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(doctor);
    }

    @GetMapping
    public ResponseEntity<List<AllDoctorsDetailsDTO>> listAllDoctor(@PageableDefault(size = 10, sort = "information.name")Pageable pageable){
        return ResponseEntity.ok().body(service.listAllDoctors(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsDTO> doctorDetails(@PathVariable Long id){
        return ResponseEntity.ok().body(service.doctorDetails(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateDoctor(@PathVariable Long id, @RequestBody UpdateDoctorDTO update) {
        service.updateDoctor(id, update);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        service.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

}