package RESTful.Hospitalapi.Infra.SecurityConfig.SecurityService;


import RESTful.Hospitalapi.Services.Users.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JWTService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    public String generateToken(Authentication authentication){
        Instant now = Instant.now();
        long expiry = 36000L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("hospital-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scopes", scopes)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getSubject(String token){
        var jwt = decoder.decode(token);
        return jwt.getSubject();
    }

    public Authentication getUserAuthenticated(String token){
        var username = userDetailsService.loadUserByUsername(getSubject(token));
        return
             new UsernamePasswordAuthenticationToken(username, null, username.getAuthorities());
    }

}
