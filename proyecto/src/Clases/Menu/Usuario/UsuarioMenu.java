package Clases.Menu.Usuario;

import Clases.Menu.Interfaces.MenuUsuario;
import Clases.Menu.Utiles.LecturaTeclado;

import java.util.Scanner;

public class UsuarioMenu implements MenuUsuario {
    @Override
    public void mostrar(Scanner teclado){
            System.out.println("=============================");
            System.out.println("\t\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Empezar entrenamiento");
            System.out.println("2) Ver mi semana");
            System.out.println("3) Ver mi historial de entrenamientos");
            System.out.println("4) Ver estadisticas");
            System.out.println("5) Actualizar informacion personal");
            System.out.println("6) Cerrar sesion");
    }

    @Override
    public int elegirOpcion(Scanner teclado) {
        return LecturaTeclado.leerEntero(teclado, 1, 6);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion) {
        switch(opcion){

        }
    }
}
