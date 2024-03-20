package RESTful.Hospitalapi.Services.Users;

import RESTful.Hospitalapi.Infra.SecurityConfig.UserAuthentication.UserAuthenticated;
import RESTful.Hospitalapi.Repositories.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return
                userRepository.findByUserName(username).map(UserAuthenticated::new).
                        orElseThrow(() -> new UsernameNotFoundException("User not Found!!"));
    }

}
