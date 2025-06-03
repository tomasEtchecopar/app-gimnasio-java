package Clases.Menu;

import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONPersona;

import java.util.Scanner;

public class LogInMenu {
    public static Persona mostrar(Scanner teclado){
        Persona usuario = null;
        System.out.println("Ingrese nombre de usuario: ");
        String nombreUsuario = teclado.nextLine();
        System.out.println("Ingrese contraseña: ");
        String contrasenia = teclado.nextLine();


        try {
            usuario = JSONPersona.getFromJSON(new Usuario(nombreUsuario, contrasenia));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }
}
