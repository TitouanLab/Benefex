package exception;

public class InvalidEmployeeEmailException extends RuntimeException {
    public InvalidEmployeeEmailException() {
        super("Invalid Employee Email format");
    }
}
