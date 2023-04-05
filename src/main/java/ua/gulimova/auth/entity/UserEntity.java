package ua.gulimova.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "UserEntity")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_entity_id")
    private Long id;

    @Column(name = "user_entity_username")
    private String username;

    @Column(name = "user_entity_password")
    private String password;

    private List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
}
