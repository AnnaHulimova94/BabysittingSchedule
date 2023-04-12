package ua.gulimova.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.gulimova.auth.authority.Authority;
import ua.gulimova.auth.authority.AuthorityRepository;
import ua.gulimova.auth.user.CustomUser;
import ua.gulimova.auth.user.CustomUserRepository;

import javax.annotation.PostConstruct;

@Component
public class SetUpper {

    private CustomUserRepository customUserRepository;

    private AuthorityRepository authorityRepository;

    public SetUpper(CustomUserRepository customUserRepository, AuthorityRepository authorityRepository) {
        this.customUserRepository = customUserRepository;
        this.authorityRepository = authorityRepository;
    }

    @PostConstruct
    void setUp() {
        CustomUser customUser = new CustomUser("anna", new BCryptPasswordEncoder().encode("guli"));
        Authority authority1 = new Authority("USER");
        Authority authority2 = new Authority("ADMIN");

        authorityRepository.save(authority1);
        authorityRepository.save(authority2);

        customUser.getAuthorityList().add(authority1);
        customUser.getAuthorityList().add(authority2);

        customUserRepository.save(customUser);
    }
}
