package Clases.Usuario;

import Clases.Acciones;

public class Usuario extends Persona  {
    public Usuario(String usuario, String contrasenia, String nombre, String apellido, int edad, double peso, double altura, boolean premium) throws IllegalArgumentException {
        super(usuario, contrasenia, nombre, apellido, edad, peso, altura, premium);
    }



}
