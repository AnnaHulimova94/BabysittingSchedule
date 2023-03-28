package ua.gulimova.employee;

import org.springframework.stereotype.Component;
import ua.gulimova.Person.PersonValidator;

@Component
public class EmployeeValidator extends PersonValidator<Employee> {

    private EmployeeRepository employeeRepository;

    public EmployeeValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isPhoneNumberUnique(String phoneNumber) {
        return employeeRepository.getByPhoneNumber(phoneNumber) == null;
    }
}
