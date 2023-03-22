package ua.gulimova.employee;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeValidator employeeValidator;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeValidator employeeValidator) {
        this.employeeRepository = employeeRepository;
        this.employeeValidator = employeeValidator;
    }

    public EmployeeResponse add(Employee employee) {
        List<String> fieldValidationMessages = employeeValidator.validate(employee);

        return fieldValidationMessages.isEmpty() ?
                new EmployeeResponse(employeeRepository.save(employee), HttpStatus.OK) :
                new EmployeeResponse(employee, HttpStatus.BAD_REQUEST, fieldValidationMessages);
    }

    public Employee get(int id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
