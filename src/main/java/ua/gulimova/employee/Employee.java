package ua.gulimova.employee;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.gulimova.Person.Person;
import ua.gulimova.auth.authority.Authority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Employee")
public class Employee extends Person {
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private long id;

    @NotNull
    @Column(name = "employee_firstName")
    private String firstName;

    @NotNull
    @Column(name = "employee_lastName")
    private String lastName;

    @NotNull
    @Column(name = "employee_phoneNumber", unique = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "custom_user_password")
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "CustomUser_Authority",
            joinColumns = {@JoinColumn(name = "custom_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<Authority> authorityList = new ArrayList<>();

    public Employee() {
    }

    public Employee(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
