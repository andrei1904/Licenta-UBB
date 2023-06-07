package domain.validators;

public class ValidatorException extends RuntimeException{
    public ValidatorException() {

    }

    public ValidatorException(String message) {
        super(message);
    }
}
