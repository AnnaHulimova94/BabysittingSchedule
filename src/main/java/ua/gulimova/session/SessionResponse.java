package ua.gulimova.session;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class SessionResponse {

    private Session session;
    private HttpStatus httpStatus;
    private List<String> fieldValidationMessages;

    public SessionResponse(Session session, HttpStatus httpStatus) {
        this.session = session;
        this.httpStatus = httpStatus;
    }

    public SessionResponse(Session session, HttpStatus httpStatus, List<String> fieldValidationMessages) {
        this.session = session;
        this.httpStatus = httpStatus;
        this.fieldValidationMessages = fieldValidationMessages;
    }
}
