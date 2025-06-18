package Clases.Menu;


import Clases.Menu.Admin.AdminMenu;
import Clases.Menu.Interfaces.MenuAcciones;
import Clases.Menu.Usuario.UsuarioMenu;
import Clases.Usuario.Usuario;

import java.util.Scanner;

public class MainMenu {

    public static void mainMenu(Scanner teclado, Usuario usuario) throws IllegalArgumentException{
        if(usuario==null){
            throw new IllegalArgumentException("El usuario (NULL) no es valido.");
        }
        limpiarConsola();

        MenuAcciones homeMenu = null;
        if (usuario.isEntrenador()){
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
