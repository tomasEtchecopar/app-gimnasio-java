package Excepciones;

public class RutinaNoExisteException extends RuntimeException {
    public RutinaNoExisteException(String message) {
        super(message);
    }
}
