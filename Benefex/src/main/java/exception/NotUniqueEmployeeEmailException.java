package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotUniqueEmployeeEmailException extends RuntimeException {
    public NotUniqueEmployeeEmailException() {
        super("This employee has already been registered.");
    }
}
