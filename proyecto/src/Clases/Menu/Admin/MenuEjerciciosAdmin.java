package Clases.Menu.Admin;

import Clases.Gimnasio.Ejercicio;
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
                    editar(teclado, teclado.nextLine());
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

    public static void editar(Scanner teclado, String nombre){
        Map<String, Ejercicio> ejercicios = JSONEjercicio.leerEjercicios();
        int opcion;

        for(Map.Entry<String, Ejercicio> ejercicioEntry: ejercicios.entrySet()){
            if(ejercicioEntry.getValue().getNombre().equals(nombre)){
                System.out.println("\n Que desea editar?\n1. Nombre\n2. Descripcion\n");
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion){
                    case 1 -> {
                        System.out.println("\nIngrese el nuevo nombre: ");
                        ejercicioEntry.getValue().setNombre(teclado.nextLine());
                    }
                    case 2 -> {
                        System.out.println("\nIngrese la nueva descripcion: ");
                        ejercicioEntry.getValue().setNombre(teclado.nextLine());
                    }
                }

            }
        }

        JSONEjercicio.editarEjercicio(ejercicios);
    }
}
