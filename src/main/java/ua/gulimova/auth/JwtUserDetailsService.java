package ua.gulimova.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.gulimova.auth.user.CustomUser;
import ua.gulimova.auth.user.CustomUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private CustomUserRepository customUserRepository;

    public JwtUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = customUserRepository.getByUsername(username);

        if (customUser != null) {
            return new User(customUser.getUsername(), customUser.getPassword(), customUser.getAuthorityList());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
