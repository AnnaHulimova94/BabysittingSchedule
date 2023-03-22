package ua.gulimova.employee;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class EmployeeValidator {

    private static final String MSG_PHONE_DUPLICATE = "Phone number is already in use";
    private static final String MSG_PHONE_INVALID = "Phone number is not correct";
    private static final String PATTERN_PHONE = "\\+([0-9]{12})";

    private EmployeeRepository employeeRepository;

    public EmployeeValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<String> validate(Employee employee) {
        List<String> fieldValidationMessages = new LinkedList<>();

        if (!isPhoneNumberUnique(employee.getPhoneNumber())) {
            fieldValidationMessages.add(MSG_PHONE_DUPLICATE);
        }

        if (!isPhoneNumberValid(employee.getPhoneNumber())) {
            fieldValidationMessages.add(MSG_PHONE_INVALID);
        }

        return fieldValidationMessages;
    }

    private boolean isPhoneNumberUnique(String phoneNumber) {
        return employeeRepository.getByTelephoneNumber(phoneNumber) == null;
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return Pattern.matches(PATTERN_PHONE, phoneNumber);
    }
}
