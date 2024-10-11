package exception;

public class EmployeeInvalidGenderException extends RuntimeException {
    public EmployeeInvalidGenderException() {
        super("Invalid gender");
    }
}
