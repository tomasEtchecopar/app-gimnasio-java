package Clases.Menu.Admin;

import Clases.Gimnasio.*;
import Clases.Menu.MainMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Menu.Utiles.Mostrado;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONEjercicio;
import Clases.manejoJSON.JSONUsuario;
import Clases.manejoJSON.JSONPlantilla;
import Excepciones.RutinaNoExisteException;
import Excepciones.RutinaYaExisteException;
import com.sun.tools.javac.Main;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.*;

import static Clases.Menu.Utiles.LecturaTeclado.leerEntero;

public class MenuRutinasAdmin {
    public static void menuRutinas(Scanner teclado){
        int opcion=-1;
        while(opcion!=5){
            MainMenu.limpiarConsola();
            System.out.println("--PLANTILLAS--");
            System.out.println("1) Ver plantillas");
            System.out.println("2) Agregar plantillas");
            System.out.println("3) Borrar plantillas");
            System.out.println("4) Editar plantillas");
            System.out.println("5) Salir");

            opcion = leerEntero(teclado, 1, 5);
            MainMenu.limpiarConsola();
            switch(opcion){
                case 1 -> mostrarPlantillasPorUsuario(teclado, seleccionarUsuarioPorId(teclado));
                case 2-> {
                    Plantilla plantilla = cargarRutinaPorTeclado(teclado);
                    try {
                        JSONPlantilla.escribirJSON(plantilla);
                    } catch(RutinaYaExisteException e){
                        System.out.println("-----ERROR: " + e.getMessage());
                    } catch(JSONException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> menuBorrarRutinas(teclado);
                case 4 ->{
                    try{
                        menuEditarRutinas(teclado);
                    }catch (RutinaNoExisteException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 5->{
                    return;
                }
            }
            LecturaTeclado.continuar(teclado);
        }
    }

    private static void menuEditarRutinas(Scanner teclado) throws RutinaNoExisteException{
        List<Plantilla> plantillas = JSONPlantilla.leerPlantillas(); //cargo plantillas
        int id = seleccionarUsuarioPorId(teclado); //selecciono de que usuario quiero ver rutinas

        if(plantillas.stream().noneMatch(p->p.getId()==id)){
            throw new RutinaNoExisteException("No hay plantillas para ese usuario.");
        }

        String nombre = mostrarPlantillasPorUsuario(teclado, id);

        Plantilla plantillaEditada =null;
        for(Plantilla planti : plantillas){
            if(planti.getNombre().equalsIgnoreCase(nombre) && planti.getId()==id){
                plantillaEditada = planti;

            }
        }

        if(plantillaEditada==null){
            System.out.println("No se pudo encontrar la plantilla.");
            return;
        }

        plantillas.remove(plantillaEditada);

        System.out.println("Ingrese que desea editar:");
        System.out.println("1) Nombre");
        System.out.println("2) ID (Trasladar hacia otro usuario)");
        System.out.println("3) Series");
        int opcion= LecturaTeclado.leerEntero(teclado, 1, 3);
        MainMenu.limpiarConsola();
        switch(opcion){
            case 1->{
                System.out.println("Ingrese nuevo nombre: ");
                String nombreNuevo = teclado.nextLine();
                plantillaEditada.setNombre(nombreNuevo);
            }
            case 2->{
                System.out.println("Ingrese a qué usuario desea trasladar la rutina");
                int idNueva= seleccionarUsuarioPorId(teclado);
                plantillaEditada.setId(idNueva);
            }
            case 3->{
                List<Serie> series = plantillaEditada.getSeries();
                plantillaEditada.mostrarRutina();
                System.out.println("Ingrese que serie desea editar: ");
                int indiceSerie = LecturaTeclado.leerEntero(teclado, 1, series.size()) - 1;
                System.out.println("Ingrese nuevo peso: ");
                series.get(indiceSerie).setPeso(LecturaTeclado.leerDouble(teclado, 0, 1000));
                System.out.println("Ingrese nuevas repeticiones: ");
                series.get(indiceSerie).setRepeticiones(LecturaTeclado.leerEntero(teclado, 0, 1000));
            }
        }

        plantillas.add(plantillaEditada);
        try {
            JSONPlantilla.sobrecargarPlantillas(plantillas);
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private static void menuBorrarRutinas(Scanner teclado){
        System.out.println("Ingrese una opcion: ");
        System.out.println("1) Borrar todas las plantillas de un usuario");
        System.out.println("2) Borrar una plantilla específica");
        System.out.println("3) Volver");
        int opcion=LecturaTeclado.leerEntero(teclado, 1,3 );
        switch(opcion) {
            case 1 -> {
                JSONPlantilla.borrarPlantillasPorId(seleccionarUsuarioPorId(teclado));
            }
            case 2 -> {
                int id=seleccionarUsuarioPorId(teclado);
                try {
                    String nombre = mostrarPlantillasPorUsuario(teclado, id);
                    JSONPlantilla.borrarPlantilla(nombre, id);
                }catch (RutinaNoExisteException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static Plantilla cargarRutinaPorTeclado(Scanner teclado) throws RuntimeException {
        int id=0;
        Map<String, Ejercicio> ejerciciosDisponibles = new HashMap<>();
        List<Usuario> usuarios = new ArrayList<>();
        List<Serie> series = new ArrayList<>();

        try{
            usuarios = JSONUsuario.getAllUsuarios();
            ejerciciosDisponibles = JSONEjercicio.getFromJSON();
        } catch (JSONException | IllegalAccessException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //se le asigna una id a la plantilla
        usuarios.sort(Comparator.comparing(Usuario::getId));
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
            int repeticiones = LecturaTeclado.leerEntero(teclado, 0, 999);

            System.out.println("Ingrese el peso en kg:");
            double peso = LecturaTeclado.leerDouble(teclado, 0, 999);

            Serie serie = new Serie(ejercicio, repeticiones, peso);
            series.add(serie);

            System.out.println("¿Desea agregar otra serie? (s/n):");
            agregar = LecturaTeclado.leerBooleanSN(teclado);
        }

        return new Plantilla(nombreRutina, series, id);
    }

    private static int seleccionarUsuarioPorId(Scanner teclado){
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = JSONUsuario.getAllUsuarios();
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        usuarios.sort(Comparator.comparing(Usuario::getId));
        MainMenu.limpiarConsola();
        System.out.println("Seleccione un usuario: ");
        System.out.println("0. Todos los usuarios");

        for(Usuario u : usuarios){
            System.out.println(u.getId() +". " +u.getNombre() +" "+ u.getApellido());
        }

        return LecturaTeclado.leerEntero(teclado, 0, usuarios.getLast().getId());
    }
    public static String mostrarPlantillasPorUsuario(Scanner teclado, int id) throws RutinaNoExisteException{
        List<Plantilla> plantillas = JSONPlantilla.leerPlantillas();
        if(plantillas.isEmpty()){
            throw new RutinaNoExisteException("No hay plantillas disponibles");
        }

        List<Plantilla> plantillasAMostrar = new ArrayList<>();
        for(Plantilla p : plantillas){
            if(p.getId()==id){
                plantillasAMostrar.add(p);
            }
        }
        return Mostrado.mostrarPlantillas(teclado, plantillasAMostrar);
    }


}

