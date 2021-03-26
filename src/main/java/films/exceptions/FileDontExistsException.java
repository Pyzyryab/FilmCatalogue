package films.exceptions;

public class FileDontExistsException extends Exception {
    
    public FileDontExistsException(String message) {
        super(message);
    }
}
