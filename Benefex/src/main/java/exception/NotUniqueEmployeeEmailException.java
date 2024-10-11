package exception;

public class NotUniqueEmployeeEmailException extends RuntimeException {
    public NotUniqueEmployeeEmailException() {
        super("This employee has already been registered.");
    }
}
