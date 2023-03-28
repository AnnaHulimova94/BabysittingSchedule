package ua.gulimova.hirer;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.gulimova.Person.Person;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Hirer")
public class Hirer extends Person {

    @Id
    @GeneratedValue
    @Column(name = "hirer_id")
    private long id;

    @NotNull
    @Column(name = "hirer_firstName")
    private String firstName;

    @NotNull
    @Column(name = "hirer_lastName")
    private String lastName;

    @NotNull
    @Column(name = "hirer_phoneNumber", unique = true)
    private String phoneNumber;

    public Hirer() {
    }
}
