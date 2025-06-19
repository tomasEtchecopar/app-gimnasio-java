package Clases.Menu.Usuario;

import Clases.Menu.Interfaces.MenuAcciones;
import Clases.Menu.MainMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Menu.Utiles.Mostrado;
import Clases.Usuario.Usuario;
import Clases.Menu.Utiles.Editores;

import java.util.Scanner;

public class UsuarioMenu implements MenuAcciones {
    @Override
    public void mostrar(Scanner teclado, Usuario usuario) {
        int opcion = -1;
        while (opcion != 6) {
            MainMenu.limpiarConsola();
            System.out.println("=============================");
            System.out.println("\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Entrenamiento");
            System.out.println("2) Ver mi historial de entrenamientos");
            System.out.println("3) Ver estadisticas");
            System.out.println("4) Ver informacion personal");
            System.out.println("5) Actualizar informacion personal");
            System.out.println("6) Cerrar sesion");
            opcion = this.elegirOpcion(teclado, usuario);
            menuCaller(teclado, opcion, usuario);
        }
    }

    @Override
    public int elegirOpcion(Scanner teclado, Usuario usuario) {
        return LecturaTeclado.leerEntero(teclado, 1, 6);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion, Usuario usuario) {
        MainMenu.limpiarConsola();
        switch (opcion) {
            case 1 -> MenuEntrenamiento.mostrar(teclado, usuario);
            case 2 -> usuario.verHistorial();
            case 3 -> Mostrado.mostrarEstadisticas(usuario);
            case 4 -> System.out.println(usuario);
            case 5 -> Editores.menuEdicionDeUsuario(teclado, usuario);
            case 6 -> {
                System.out.println("Hasta luego!");;
            }
        }
        LecturaTeclado.continuar(teclado);
    }


    }

