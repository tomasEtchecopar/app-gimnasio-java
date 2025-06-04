package Clases.Menu.Admin;

import Clases.Gimnasio.Ejercicio;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Menu.Interfaces.MenuUsuario;
import Clases.manejoJSON.JSONEjercicio;
import org.json.JSONException;

import java.util.Map;
import java.util.Scanner;

import static Clases.Menu.Utiles.LecturaTeclado.leerEntero;

public class AdminMenu implements MenuUsuario {

    @Override
    public void mostrar(Scanner teclado) {
        int opcion = -1;
        while (opcion != 6) {
            System.out.println("=============================");
            System.out.println("\t\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido, ADMIN!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Menu ejercicios");
            System.out.println("2) Menu rutinas");
            System.out.println("3) Menu usuarios");
            System.out.println("4) Cerrar sesion");
        }
    }

    @Override
    public int elegirOpcion(Scanner teclado) {
        return LecturaTeclado.leerEntero(teclado, 1, 4);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion) {
        switch(opcion){
            case 1 -> menuEjercicios(teclado);
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
