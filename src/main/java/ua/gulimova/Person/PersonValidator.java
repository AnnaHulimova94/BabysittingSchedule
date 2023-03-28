package ua.gulimova;

import org.springframework.stereotype.Component;
import ua.gulimova.employee.EmployeeRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class PersonValidator<T extends Person> {

    private static final String MSG_PHONE_DUPLICATE = "Phone number is already in use";
    private static final String MSG_PHONE_INVALID = "Phone number is not correct";
    private static final String PATTERN_PHONE = "\\+([0-9]{12})";

    private PersonRepository personRepository;

    public PersonValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<String> validate(T person) {
        List<String> fieldValidationMessages = new LinkedList<>();

        if (!isPhoneNumberUnique(person.getPhoneNumber())) {
            fieldValidationMessages.add(MSG_PHONE_DUPLICATE);
        }

        if (!isPhoneNumberValid(person.getPhoneNumber())) {
            fieldValidationMessages.add(MSG_PHONE_INVALID);
        }

        return fieldValidationMessages;
    }

    private boolean isPhoneNumberUnique(String phoneNumber) {
        return personRepository.getByTelephoneNumber(phoneNumber) == null;
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return Pattern.matches(PATTERN_PHONE, phoneNumber);
    }
}
