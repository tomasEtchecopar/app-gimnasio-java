package Clases.Usuario;

import java.util.Scanner;


public class Admin extends Persona {
    public Admin(String usuario, String contrasenia, String nombre, String apellido, int edad, double peso, double altura, boolean premium) {
        super(usuario, contrasenia, nombre, apellido, edad, peso, altura, premium);
    }

    public Admin(String usuario, String contrasenia) throws IllegalArgumentException {
        super(usuario, contrasenia);
    }

    public Admin() {
    }

    @Override
    public void entrenar(Scanner teclado) {
        System.out.println("El administrador no puede registrar un entrenamiento.");
    }

}
