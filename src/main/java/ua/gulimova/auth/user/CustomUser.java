package ua.gulimova.auth.user;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public CustomUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
