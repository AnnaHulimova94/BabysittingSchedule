package ua.gulimova.employee;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Setter
@Getter
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue
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
