package Clases.Usuario;

import Clases.Acciones;
import Clases.Gimnasio.Entrenamiento;

import java.util.List;

public class Usuario extends Persona  {
    public Usuario(String usuario, String contrasenia, String nombre, String apellido, int edad, double peso, double altura, boolean premium) throws IllegalArgumentException {
        super(usuario, contrasenia, nombre, apellido, edad, peso, altura, premium);
    }

    public Usuario(String usuario, String contrasenia, String nombre, String apellido, int edad, double peso, double altura, boolean premium, List<Entrenamiento> historial) throws IllegalArgumentException {
        super(usuario, contrasenia, nombre, apellido, edad, peso, altura, premium, historial);
    }

    public Usuario(String usuario, String contrasenia) throws IllegalArgumentException {
        super(usuario, contrasenia);
    }

    public Usuario() {
    }
}
