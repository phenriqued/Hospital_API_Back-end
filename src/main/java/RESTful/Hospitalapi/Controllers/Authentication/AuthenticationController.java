package RESTful.Hospitalapi.Controllers.Authentication;


import RESTful.Hospitalapi.DTOs.UserAuthenticated.UserAuthenticatedDTO;
import RESTful.Hospitalapi.Services.Authenticate.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager manager;


    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserAuthenticatedDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.userName(), dto.password());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().body(authenticationService.authenticate(authentication));
    }



}
