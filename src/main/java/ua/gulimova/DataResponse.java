package ua.gulimova.Person;

import lombok.Data;
import org.springframework.http.HttpStatus;
import ua.gulimova.employee.Employee;

import java.util.List;

@Data
public class PersonResponse<T> {
    private T person;
    private HttpStatus httpStatus;
    private List<String> fieldValidationMessages;

    public PersonResponse() {}

    public PersonResponse(T person, HttpStatus httpStatus) {
        this.person = person;
        this.httpStatus = httpStatus;
    }

    public PersonResponse(T person, HttpStatus httpStatus, List<String> fieldValidationMessages) {
        this.person = person;
        this.httpStatus = httpStatus;
        this.fieldValidationMessages = fieldValidationMessages;
    }
}
