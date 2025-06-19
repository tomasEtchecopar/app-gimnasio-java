package Clases.Menu.Admin;

import Clases.Gimnasio.Ejercicio;
import Clases.Gimnasio.GrupoMuscular;
import Clases.Menu.MainMenu;
import Clases.Menu.Utiles.Editores;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Menu.Utiles.Mostrado;
import Clases.manejoJSON.JSONEjercicio;

import java.util.*;

import static Clases.Menu.Utiles.LecturaTeclado.leerEntero;

public class MenuEjerciciosAdmin {
    public static void menuEjercicios(Scanner teclado){
        int opcion=-1;
        while(opcion!=5){

            MainMenu.limpiarConsola();
            System.out.println("--EJERCICIOS--");
            System.out.println("1) Ver ejercicios");
            System.out.println("2) Agregar Ejercicio");
            System.out.println("3) Salir");
            List<Ejercicio> ejercicios = JSONEjercicio.leerEjercicios().values().stream().toList();
            if(!ejercicios.isEmpty()) {
                System.out.println("4) Editar Ejercicio");
                System.out.println("5) Borrar Ejercicio");
                opcion = leerEntero(teclado, 1, 5);
            }else{
                opcion=leerEntero(teclado, 1 , 3);
            }



            switch(opcion){
                case 1 -> Mostrado.verEjercicios(teclado);
                case 2 -> {
                    Ejercicio ejercicioNuevo = cargarEjercicioPorTeclado(teclado);
                    JSONEjercicio.guardarEjercicio(ejercicioNuevo);
                }
                case 3 -> {
                    return;
                }
                case 4 -> {
                    for (int i = 0; i < ejercicios.size(); i++) {
                        System.out.println(i+". "+ejercicios.get(i));
                    }
                    System.out.println("\n Ingrese el ejercicio a editar: ");
                    int index = LecturaTeclado.leerEntero(teclado, 0, ejercicios.size()-1);
                    Editores.editarEjercicio(teclado, ejercicios.get(index).getNombre());
                }
                case 5->{for (int i = 0; i < ejercicios.size(); i++) {
                    System.out.println(i+". "+ejercicios.get(i));
                }
                    System.out.println("\n Ingrese el ejercicio a borrar: ");
                    int index = LecturaTeclado.leerEntero(teclado, 0, ejercicios.size()-1);
                    JSONEjercicio.borrarEjercicio(ejercicios.get(index).getNombre());}
            }
            LecturaTeclado.continuar(teclado);
        }
    }



    public static Ejercicio cargarEjercicioPorTeclado(Scanner teclado){
        System.out.println("Ingrese el nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese el grupo muscular:");

        GrupoMuscular[] grupos = GrupoMuscular.values();
        for (int i = 0; i < grupos.length; i++) {
            System.out.println((i+1)+") "+grupos[i]);
        }
        int elegido = (LecturaTeclado.leerEntero(teclado, 0, grupos.length)) -  1;

        System.out.println("Ingrese la descripción: ");
        String descripcion = teclado.nextLine();
        return new Ejercicio(nombre, grupos[elegido], descripcion);
    }
}
