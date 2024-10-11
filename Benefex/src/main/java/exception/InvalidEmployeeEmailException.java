package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmployeeEmailException extends RuntimeException {
    public InvalidEmployeeEmailException() {
        super("Invalid Employee Email format");
    }
}
