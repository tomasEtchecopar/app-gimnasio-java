package Excepciones;

public class RutinaYaExisteException extends RuntimeException {
    public RutinaYaExisteException(String message) {
        super(message);
    }
}
