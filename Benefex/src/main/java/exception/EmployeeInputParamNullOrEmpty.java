package exception;

public class EmployeeInputParamNullOrEmpty extends RuntimeException {
    public EmployeeInputParamNullOrEmpty(String paramName) {
        super("Employee " + paramName + " is null or empty");
    }
}
