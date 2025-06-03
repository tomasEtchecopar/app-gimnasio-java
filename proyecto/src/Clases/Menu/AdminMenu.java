package Clases.Menu;

import Clases.Gimnasio.Ejercicio;
import Clases.Usuario.Persona;
import Clases.manejoJSON.JSONEjercicio;
import org.json.JSONException;

import java.util.Map;
import java.util.Scanner;

import static Clases.Menu.LecturaTeclado.leerEntero;

public class AdminMenu {

    public static void mostrar(Scanner teclado, Persona usuario) {
        int opcion = -1;
        while (opcion != 6) {
            System.out.println("=============================");
            System.out.println("\t\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido, " + usuario.getNombre() + "!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Menu ejercicios");
            System.out.println("2) Menu rutinas");
            System.out.println("3) ");
            System.out.println("4) Ver usuarios");
            System.out.println("5) Actualizar informacion personal");
            System.out.println("6) Cerrar sesion");

            opcion = leerEntero(teclado, 1, 6);
            switch (opcion) {
                case 1 -> menuEjercicios(teclado);
            }
        }
    }

    /// FUNCIONALIDADES EXCLUSIVAS DEL ADMIN
    private static void menuEjercicios(Scanner teclado){
        int opcion=-1;
        while(opcion!=5){
            System.out.println("--EJERCICIOS--");
            System.out.println("1) Ver ejercicios");
            System.out.println("2) Agregar Ejercicio");
            System.out.println("3) Borrar Ejercicio");
            System.out.println("4) Editar Ejercicio");
            System.out.println("5) Salir");

            opcion = leerEntero(teclado, 1, 5);
            switch(opcion){
                case 1 -> mostrarEjercicios();
                case 2 -> {
                    try {
                        JSONEjercicio.escribirJSON(cargarEjercicioPorTeclado(teclado));
                    } catch (JSONException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static void mostrarEjercicios(){
        try {
            Map<String, Ejercicio> ejercicios = JSONEjercicio.getFromJSON();
            for(Ejercicio ej : ejercicios.values()){
                System.out.println("--------------------\n");
                System.out.println("Nombre: " + ej.getNombre());
                System.out.println("Descripcion: " +ej.getDescripcion());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static Ejercicio cargarEjercicioPorTeclado(Scanner teclado){
        System.out.println("Ingrese el nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese la descripción: ");
        String descripcion = teclado.nextLine();
        return new Ejercicio(nombre, descripcion);
    }

}
