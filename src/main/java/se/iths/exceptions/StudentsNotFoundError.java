package se.iths.exceptions;

public class StudentsNotFoundError {

    private String message;

    public StudentsNotFoundError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
