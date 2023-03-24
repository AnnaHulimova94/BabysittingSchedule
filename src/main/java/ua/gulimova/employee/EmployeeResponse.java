package ua.gulimova.employee;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
class EmployeeResponse {
    private Employee employee;
    private HttpStatus httpStatus;
    private List<String> fieldValidationMessages;

    EmployeeResponse(Employee employee, HttpStatus httpStatus) {
        this.employee = employee;
        this.httpStatus = httpStatus;
    }

    EmployeeResponse(Employee employee, HttpStatus httpStatus, List<String> fieldValidationMessages) {
        this.employee = employee;
        this.httpStatus = httpStatus;
        this.fieldValidationMessages = fieldValidationMessages;
    }
}
