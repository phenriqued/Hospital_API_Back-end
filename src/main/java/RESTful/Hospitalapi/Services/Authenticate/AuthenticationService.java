package RESTful.Hospitalapi.Services.Authenticate;

import RESTful.Hospitalapi.Infra.SecurityConfig.SecurityService.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    @Autowired
    private JWTService jwtService;

    public String authenticate(Authentication authentication){
        return jwtService.generateToken(authentication);
    }


}
