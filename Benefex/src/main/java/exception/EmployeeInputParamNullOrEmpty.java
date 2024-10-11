package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeInputParamNullOrEmpty extends RuntimeException {
    public EmployeeInputParamNullOrEmpty(String paramName) {
        super("Employee " + paramName + " is null or empty");
    }
}
