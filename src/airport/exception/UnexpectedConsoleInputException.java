package airport.exception;

public class UnexpectedConsoleInputException extends RuntimeException{
    public UnexpectedConsoleInputException(String message){
        super(message);
    }
}
