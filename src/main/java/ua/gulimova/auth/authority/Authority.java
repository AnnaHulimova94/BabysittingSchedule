package ua.gulimova.auth.authority;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Authority")
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    private long authority_id;

    @NotNull
    @Column(name = "authority_name")
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }
}
