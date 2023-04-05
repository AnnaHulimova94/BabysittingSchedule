package ua.gulimova.Person;

import org.springframework.stereotype.Component;
import ua.gulimova.util.ValidationMSG;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public abstract class PersonValidator<T extends Person> {

    public List<String> validate(T person) {
        List<String> fieldValidationMessages = new LinkedList<>();

        if (!isPhoneNumberUnique(person.getPhoneNumber())) {
            fieldValidationMessages.add(ValidationMSG.MSG_PHONE_DUPLICATE);
        }

        if (!isPhoneNumberValid(person.getPhoneNumber())) {
            fieldValidationMessages.add(ValidationMSG.MSG_PHONE_INVALID);
        }

        return fieldValidationMessages;
    }

    public abstract boolean isPhoneNumberUnique(String phoneNumber);

    private boolean isPhoneNumberValid(String phoneNumber) {
        return Pattern.matches(ValidationMSG.PATTERN_PHONE, phoneNumber);
    }
}
