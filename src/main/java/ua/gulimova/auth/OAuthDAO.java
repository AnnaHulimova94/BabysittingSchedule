package ua.gulimova.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import ua.gulimova.auth.entity.UserEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OAuthDAO {

    private UserEntityRepository userEntityRepository;

    public OAuthDAO(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public UserEntity getUserDetails(String username) {
        return userEntityRepository.getByUsername(username);
    }
}
