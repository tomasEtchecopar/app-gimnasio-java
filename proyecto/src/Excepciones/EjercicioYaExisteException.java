package Excepciones;

public class EjercicioYaExisteException extends RuntimeException {
    public EjercicioYaExisteException(String message) {
        super(message);
    }
}
