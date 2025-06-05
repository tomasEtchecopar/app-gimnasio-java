package Clases.Menu.Usuario;

import Clases.Menu.Interfaces.MenuUsuario;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;

import java.util.Scanner;

public class UsuarioMenu implements MenuUsuario {
    @Override
    public void mostrar(Scanner teclado, Persona usuario) {
        int opcion = -1;
        while (opcion != 6) {
            System.out.println("=============================");
            System.out.println("\t\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Empezar entrenamiento");
            System.out.println("2) Ver mi historial de entrenamientos");
            System.out.println("3) Ver estadisticas");
            System.out.println("4) Actualizar informacion personal");
            System.out.println("5) Cerrar sesion");
            opcion = this.elegirOpcion(teclado, usuario);
            menuCaller(teclado, opcion, usuario);
        }
    }

    @Override
    public int elegirOpcion(Scanner teclado, Persona usuario) {
        return LecturaTeclado.leerEntero(teclado, 1, 6);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion, Persona usuario) {
        MenuUsuario menuElegido = null;
        switch(opcion){
            case 1 -> {
                usuario.entrenar(teclado);
            }
            case 2-> usuario.verHistorial();
            case 3 -> System.out.println("Aun no implementado");
            case 4 -> System.out.println("Aun no implementado");
        }

    }
}
