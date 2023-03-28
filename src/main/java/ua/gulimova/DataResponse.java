package ua.gulimova;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Serialization
public class DataResponse<T> {
    private T data;
    private HttpStatus httpStatus;
    private List<String> fieldValidationMessages;

    public DataResponse() {}

    public DataResponse(T data, HttpStatus httpStatus) {
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public DataResponse(T data, HttpStatus httpStatus, List<String> fieldValidationMessages) {
        this.data = data;
        this.httpStatus = httpStatus;
        this.fieldValidationMessages = fieldValidationMessages;
    }
}
