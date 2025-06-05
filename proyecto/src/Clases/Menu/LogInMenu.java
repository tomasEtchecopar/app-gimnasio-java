package Clases.Menu;

import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONPersona;

import java.util.Scanner;

public class LogInMenu {
    public static Persona mostrar(Scanner teclado){
        System.out.println("Ingrese nombre de usuario: ");
        String nombreUsuario = teclado.nextLine();
        System.out.println("Ingrese contraseña: ");
        String contrasenia = teclado.nextLine();

        return JSONPersona.iniciarSesion(nombreUsuario, contrasenia);
    }



}
