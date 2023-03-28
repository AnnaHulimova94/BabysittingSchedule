package ua.gulimova.employee;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.gulimova.DataResponse;
import ua.gulimova.Person.PersonValidator;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PersonValidator<Employee> personValidator;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeValidator personValidator) {
        this.employeeRepository = employeeRepository;
        this.personValidator = personValidator;
    }

    public DataResponse<Employee> add(Employee employee) {
        List<String> fieldValidationMessages = personValidator.validate(employee);

        return fieldValidationMessages.isEmpty() ?
                new DataResponse<>(employeeRepository.save(employee), HttpStatus.OK) :
                new DataResponse<>(employee, HttpStatus.BAD_REQUEST, fieldValidationMessages);
    }

    public DataResponse<Employee> get(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return new DataResponse<>(null, HttpStatus.NOT_FOUND);
        }

        return new DataResponse<>(employee, HttpStatus.OK);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
