package exception;

public class EmployeeEmailNullOrEmpty extends RuntimeException{
    public EmployeeEmailNullOrEmpty() {
        super("Employee email is null or empty");
    }
}
