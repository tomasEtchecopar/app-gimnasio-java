package Clases.Menu.Admin;

import Clases.Gimnasio.*;
import Clases.Menu.MainMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONEjercicio;
import Clases.manejoJSON.JSONPersona;
import Clases.manejoJSON.JSONPlantilla;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.*;

import static Clases.Menu.Utiles.LecturaTeclado.leerEntero;

public class MenuRutinasAdmin {
    public static void menuRutinas(Scanner teclado){
        int opcion=-1;
        while(opcion!=5){
            MainMenu.limpiarConsola();
            System.out.println("--RUTINAS--");
            System.out.println("1) Ver rutinas");
            System.out.println("2) Agregar rutina");
            System.out.println("3) Borrar rutina");
            System.out.println("4) Editar rutina");
            System.out.println("5) Salir");

            opcion = leerEntero(teclado, 1, 5);
            MainMenu.limpiarConsola();
            switch(opcion){
                case 1 -> mostrarPlantillasPorUsuario(teclado);
                case 2-> {
                    Plantilla plantilla = cargarRutinaPorTeclado(teclado);
                    try {
                        JSONPlantilla.escribirJSON(plantilla);
                    } catch (JSONException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            LecturaTeclado.continuar(teclado);
        }
    }

    private static Plantilla cargarRutinaPorTeclado(Scanner teclado) throws RuntimeException {
        int id=0;
        Map<String, Ejercicio> ejerciciosDisponibles = new HashMap<>();
        List<Usuario> usuarios = new ArrayList<>();
        List<Serie> series = new ArrayList<>();

        try{
            usuarios = JSONPersona.getAllUsuarios();
            ejerciciosDisponibles = JSONEjercicio.getFromJSON();
        } catch (JSONException | IllegalAccessException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //se le asigna una id a la plantilla
        usuarios.sort(Comparator.comparing(Persona::getId));
        usuarios.removeFirst();
        System.out.println("Ingrese para que usuario es la rutina: ");
        System.out.println("0. Todos los usuarios");

        for(Usuario u : usuarios){
            System.out.println(u.getId() +". " +u.getNombre() +" "+ u.getApellido());
        }
        id= LecturaTeclado.leerEntero(teclado, 0, usuarios.getLast().getId());

        //nombre de la plantilla
        System.out.println("Ingrese el nombre de la rutina:");
        String nombreRutina = teclado.nextLine();

        //para mostrar ejercicios
        List<String> nombresEjercicios = new ArrayList<>(ejerciciosDisponibles.keySet());
        boolean agregar = true;

        while (agregar) {
            System.out.println("Seleccione un ejercicio disponible:");
            for (int i = 0; i < nombresEjercicios.size(); i++) {
                System.out.println(i + ". " + nombresEjercicios.get(i));
            }
            int seleccion = LecturaTeclado.leerEntero(teclado, 0, nombresEjercicios.size() - 1);
            Ejercicio ejercicio = ejerciciosDisponibles.get(nombresEjercicios.get(seleccion));

            System.out.println("Ingrese la cantidad de repeticiones:");
            int repeticiones = Integer.parseInt(teclado.nextLine());

            System.out.println("Ingrese el peso en kg:");
            double peso = Double.parseDouble(teclado.nextLine());

            Serie serie = new Serie(ejercicio, repeticiones, peso);
            series.add(serie);

            System.out.println("¿Desea agregar otra serie? (s/n):");
            char respuesta = teclado.nextLine().toLowerCase().charAt(0);
            agregar = respuesta =='s';
        }

        return new Plantilla(nombreRutina, series, id);
    }

    private static void mostrarPlantillasPorUsuario(Scanner teclado){
        List<Plantilla> plantillas = JSONPlantilla.leerPlantillas();
        if(plantillas.isEmpty()){
            System.out.println("No hay plantillas disponibles");
            return;
        }
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = JSONPersona.getAllUsuarios();
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        usuarios.sort(Comparator.comparing(Persona::getId));
        usuarios.removeFirst();
        System.out.println("Seleccione que plantillas desea ver: ");
        System.out.println("0. Todos los usuarios");

        for(Usuario u : usuarios){
            System.out.println(u.getId() +". " +u.getNombre() +" "+ u.getApellido());
        }

        int id= LecturaTeclado.leerEntero(teclado, 0, usuarios.getLast().getId());

        List<Plantilla> plantillasAMostrar = new ArrayList<>();
        for(Plantilla p : plantillas){
            if(p.getId()==id){
                plantillasAMostrar.add(p);
            }
        }
        mostrarPlantillas(teclado, plantillasAMostrar);
    }

    private static void mostrarPlantillas(Scanner teclado, List<Plantilla> plantillas){
        if(plantillas.isEmpty()){
            return;
        }
        System.out.println("Seleccione que plantilla desea ver: ");
        for (int i = 0; i < plantillas.size(); i++) {
            System.out.println(i+". "+plantillas.get(i).getNombre());
        }
        int seleccion = LecturaTeclado.leerEntero(teclado, 0, plantillas.size()-1);
        plantillas.get(seleccion).mostrarRutina();
    }

}

