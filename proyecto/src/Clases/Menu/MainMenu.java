package Clases.Menu;


import Clases.Menu.Admin.AdminMenu;
import Clases.Menu.Interfaces.MenuUsuario;
import Clases.Menu.Usuario.UsuarioMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Persona;

import java.util.Scanner;

public class MainMenu {

    public static void run() {
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        Persona usuario;
        System.out.println("Bienvenido a nuestra app de entrenamineto");
        while (!salir) {
            System.out.println("1. Iniciar Sesion.");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            int opcion =  LecturaTeclado.leerEntero(teclado, 1, 3);

            switch (opcion) {
                case 1 -> {
                    usuario = LogInMenu.mostrar(teclado);
                    mainMenu(teclado, usuario);
                }
                case 2 -> RegistroMenu.mostrar(teclado);
                case 3 -> salir = true;
            }
        }
        teclado.close();
    }

    private static void mainMenu(Scanner teclado, Persona usuario) throws IllegalArgumentException{
        if(usuario==null){
            throw new IllegalArgumentException("El usuario (NULL) no es valido.");
        }

        MenuUsuario menuDelUsuario;
        if (usuario.getUsuario().equals("admin")){
            menuDelUsuario = new AdminMenu();
            menuDelUsuario.mostrar(teclado, usuario);
        }
        else new UsuarioMenu().mostrar(teclado, usuario);

    }
    public static void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println(" ");
        }
    }








}
