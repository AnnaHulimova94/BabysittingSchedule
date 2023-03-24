package ua.gulimova.employee;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ua.gulimova.session.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private int id;

    @NotNull
    @Column(name = "employee_firstName")
    private String firstName;

    @NotNull
    @Column(name = "employee_lastName")
    private String lastName;

    @NotNull
    @Column(name = "employee_phoneNumber", unique = true)
    private String phoneNumber;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
