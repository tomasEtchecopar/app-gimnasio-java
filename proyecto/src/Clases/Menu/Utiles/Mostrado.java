package Clases.Menu.Utiles;

import Clases.Gimnasio.Ejercicio;
import Clases.Gimnasio.Plantilla;
import Clases.Menu.MainMenu;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONEjercicio;

import java.util.*;

public class Mostrado {
    public static String mostrarPlantillas(Scanner teclado, List<Plantilla> plantillas){
        if(plantillas.isEmpty()){
            return null;
        }
        System.out.println("Seleccione plantilla: ");
        for (int i = 0; i < plantillas.size(); i++) {
            System.out.println(i+". "+plantillas.get(i).getNombre());
        }
        int seleccion = LecturaTeclado.leerEntero(teclado, 0, plantillas.size()-1);
        plantillas.get(seleccion).mostrarRutina();
        return plantillas.get(seleccion).getNombre();
    }
    public static void verEjercicios(Scanner teclado){
        int opcion=-1;
        Map<String, Ejercicio> ejercicios = JSONEjercicio.leerEjercicios();

        if(ejercicios.isEmpty()){
            System.out.println("No hay ejercicios en ejercicios.json");
            return;
        }


        List<Ejercicio> ejerciciosOrdenados =  new ArrayList<>();
        MainMenu.limpiarConsola();
        System.out.println("Ingrese en que orden desea ver los ejercicios: ");
        System.out.println("1) Ordenar por nombre");
        System.out.println("2) Ordenar por grupo muscular");
        System.out.println("3) Volver");
        opcion=LecturaTeclado.leerEntero(teclado, 1, 3);
        switch(opcion){
            case 1 ->ejerciciosOrdenados =  ejercicios.values().stream().sorted(Comparator.comparing(e->e.getNombre())).toList();
            case 2 ->ejerciciosOrdenados = ejercicios.values().stream().sorted(Comparator.comparing(e -> e.getGrupoMuscular())).toList();
            case 3 ->{
                return;
            }
        }

        for(Ejercicio ej : ejerciciosOrdenados){
            System.out.println("--------------------\n");
            System.out.println("Nombre: " + ej.getNombre());
            System.out.println("Grupo muscular: " + ej.getGrupoMuscular());
            System.out.println("Descripcion: " +ej.getDescripcion());
        }


    }
    public static void mostrarEstadisticas(Usuario usuario) {
        System.out.println("--Estadisticas de " + usuario.getNombre());
        System.out.println("Edad: " + usuario.getEdad()+" años.");
        System.out.println("Peso: " + usuario.getPeso()+" kg.");
        System.out.println("Altura: " + usuario.getAltura()+" cm.");
        System.out.printf("Indice de masa corporal: %f.2", usuario.getIMC() );
        System.out.println("\n");
        switch (usuario.tieneSobrepeso()) {
            case 1 -> System.out.println("Sobrepeso");
            case 0 -> System.out.println("Peso ideal");
            case -1 -> System.out.println("Peso bajo");
        }
    }
}
