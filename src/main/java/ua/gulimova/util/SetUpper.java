package ua.gulimova.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.gulimova.auth.user.CustomUser;
import ua.gulimova.auth.user.CustomUserRepository;

import javax.annotation.PostConstruct;

@Component
public class SetUpper {

    private CustomUserRepository customUserRepository;

    public SetUpper(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @PostConstruct
    void setUp() {
        CustomUser customUser = new CustomUser("anna", new BCryptPasswordEncoder().encode("guli"));
        customUserRepository.save(customUser);
    }
}
