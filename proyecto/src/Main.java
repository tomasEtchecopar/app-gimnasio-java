import Clases.Menu.LogInMenu;
import Clases.Menu.MainMenu;
import Clases.Menu.RegistroMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Usuario;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        Usuario usuario;

        while (!salir) {
            MainMenu.limpiarConsola();
            System.out.println("Bienvenido a nuestra app de entrenamineto");
            System.out.println("1. Iniciar Sesion.");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            int opcion =  LecturaTeclado.leerEntero(teclado, 1, 3);

            MainMenu.limpiarConsola();
            switch (opcion) {
                case 1 -> {
                    usuario = LogInMenu.mostrar(teclado);
                    if(usuario!=null) {
                        MainMenu.mainMenu(teclado, usuario);
                    }else{
                        LecturaTeclado.continuar(teclado);
                    }
                }
                case 2 -> RegistroMenu.mostrar(teclado);
                case 3 -> salir = true;
            }
        }
        teclado.close();
    }

}