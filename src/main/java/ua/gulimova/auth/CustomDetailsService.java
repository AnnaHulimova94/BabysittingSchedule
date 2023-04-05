package ua.gulimova.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.gulimova.auth.OAuthDAO;
import ua.gulimova.auth.entity.CustomUser;
import ua.gulimova.auth.entity.UserEntity;

public class CustomDetailsService implements UserDetailsService {

    private OAuthDAO oAuthDAO;

    public CustomDetailsService(OAuthDAO oAuthDAO) {
        this.oAuthDAO = oAuthDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = oAuthDAO.getUserDetails(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        return new CustomUser(userEntity);
    }
}
