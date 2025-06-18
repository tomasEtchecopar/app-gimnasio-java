package Clases.Menu;

import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONUsuario;

import java.util.Scanner;

public class LogInMenu {
    public static Usuario mostrar(Scanner teclado){
        System.out.println("Ingrese nombre de usuario: ");
        String nombreUsuario = teclado.nextLine();
        System.out.println("Ingrese contraseña: ");
        String contrasenia = teclado.nextLine();

        return JSONUsuario.iniciarSesion(nombreUsuario, contrasenia);
    }



}
