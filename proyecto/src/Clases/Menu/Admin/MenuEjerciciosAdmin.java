package Clases.Menu.Admin;

import Clases.Gimnasio.Ejercicio;
import Clases.Menu.Utiles.Editores;
import Clases.manejoJSON.JSONEjercicio;
import org.json.JSONException;

import java.util.Map;
import java.util.Scanner;

import static Clases.Menu.Utiles.LecturaTeclado.leerEntero;

public class MenuEjerciciosAdmin {
    public static void menuEjercicios(Scanner teclado){
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
                    Ejercicio ejercicioNuevo = cargarEjercicioPorTeclado(teclado);
                    JSONEjercicio.guardarEjercicio(ejercicioNuevo);
                }
                case 3 -> {
                    System.out.println("\n Ingrese el ejercicio a borrar: ");
                    JSONEjercicio.borrarEjercicio(teclado.nextLine());
                }
                case 4 -> {
                    System.out.println("\n Ingrese el ejercicio a editar: ");
                    Editores.editarEjercicio(teclado, teclado.nextLine());
                }
                default -> System.out.println("\n Ocurrio un error\n\n");
            }
        }
    }

    public static void mostrarEjercicios(){
        Map<String, Ejercicio> ejercicios = JSONEjercicio.leerEjercicios();
        if(ejercicios.isEmpty()){
            System.out.println("No hay ejercicios en ejercicios.json");
            return;
        }
        for(Ejercicio ej : ejercicios.values()){
            System.out.println("--------------------\n");
            System.out.println("Nombre: " + ej.getNombre());
            System.out.println("Descripcion: " +ej.getDescripcion());
        }
    }

    public static Ejercicio cargarEjercicioPorTeclado(Scanner teclado){
        System.out.println("Ingrese el nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese la descripción: ");
        String descripcion = teclado.nextLine();
        return new Ejercicio(nombre, descripcion);
    }
}
