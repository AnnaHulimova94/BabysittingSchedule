package ua.gulimova.auth.user;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.gulimova.auth.authority.Authority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CustomUser")
@NoArgsConstructor
public class CustomUser {

    @Id
    @GeneratedValue
    @Column(name = "custom_user_id")
    private long id;

    @NotNull
    @Column(name = "custom_user_username", unique = true)
    private String username;

    @NotNull
    @Column(name = "custom_user_password")
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "CustomUser_Authority",
            joinColumns = {@JoinColumn(name = "custom_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<Authority> authorityList = new ArrayList<>();

    public CustomUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
