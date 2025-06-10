package Clases.Menu;


import Clases.Menu.Admin.AdminMenu;
import Clases.Menu.Interfaces.MenuUsuario;
import Clases.Menu.Usuario.UsuarioMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Persona;

import java.util.Scanner;

public class MainMenu {

    public static void mainMenu(Scanner teclado, Persona usuario) throws IllegalArgumentException{
        if(usuario==null){
            throw new IllegalArgumentException("El usuario (NULL) no es valido.");
        }
        limpiarConsola();

        MenuUsuario homeMenu = null;
        if (usuario.getUsuario().equals("admin")){
            homeMenu = new AdminMenu();
        }
        else{
            homeMenu = new UsuarioMenu();
        }
        homeMenu.mostrar(teclado, usuario);

    }

    public static void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println(" ");
        }
    }
}
