package RESTful.Hospitalapi.Controllers.Authentication;


import RESTful.Hospitalapi.DTOs.UserAuthenticated.UserAuthenticatedDTO;
import RESTful.Hospitalapi.Infra.SecurityConfig.TokenDTO.TokenDTO;
import RESTful.Hospitalapi.Services.Authenticate.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager manager;


    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserAuthenticatedDTO dto){
        var tokenAuthenticate = new UsernamePasswordAuthenticationToken(dto.userName(), dto.password());
        var authentication = manager.authenticate(tokenAuthenticate);
        var token = new TokenDTO(authenticationService.authenticate(authentication));

        return ResponseEntity.ok().body(token);
    }



}
