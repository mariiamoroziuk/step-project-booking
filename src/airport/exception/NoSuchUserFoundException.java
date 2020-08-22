package airport.exception;

public class NoSuchUserFoundException extends RuntimeException{
    public NoSuchUserFoundException(){
        super("Пользователь с таким логином и паролем не найден в системе");
    }
}
