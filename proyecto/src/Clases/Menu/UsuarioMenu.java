package Clases.Menu;

import Clases.Usuario.Persona;

import java.util.Scanner;

public class UsuarioMenu {
    public static void mostrar(Scanner teclado, Persona usuario){
            System.out.println("=============================");
            System.out.println("\t\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido, " + usuario.getNombre() +"!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Empezar entrenamiento");
            System.out.println("2) Ver mi semana");
            System.out.println("3) Ver mi historial de entrenamientos");
            System.out.println("4) Ver estadisticas");
            System.out.println("5) Actualizar informacion personal");
            System.out.println("6) Cerrar sesion");
        }
    }
}
