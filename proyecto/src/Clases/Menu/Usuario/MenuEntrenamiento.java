package Clases.Menu.Usuario;

import Clases.Gimnasio.Entrenamiento;
import Clases.Gimnasio.Plantilla;
import Clases.Gimnasio.Serie;
import Clases.Menu.Interfaces.MenuUsuario;
import Clases.Menu.MainMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Persona;
import Clases.manejoJSON.JSONPlantilla;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuEntrenamiento {
    public static void mostrar(Scanner teclado, Persona usuario) {
        int opcion = -1;
        while (opcion != 4) {
            MainMenu.limpiarConsola();
            System.out.println("=============================");
            System.out.println("\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("1) Empezar entrenamiento");
            System.out.println("2) Ver catalogo de ejercicios");
            System.out.println("3) Ver catalogo de rutinas");
            System.out.println("4) Volver");
            opcion = LecturaTeclado.leerEntero(teclado, 1, 4);
            menuCaller(teclado, opcion, usuario);
        }
    }

    private static void menuCaller(Scanner teclado, int opcion, Persona usuario) {
        MainMenu.limpiarConsola();
        switch(opcion){
            case 1->usuario.entrenar(teclado);
            //case 2 -> //mostrado de ejercicios (filtrar y ordenar por nombre y grupo)
            //case 3 -> //mostrado de rutinas
            case 4->{
                return;
            }
        }
        LecturaTeclado.continuar(teclado);
    }

    private static Plantilla elegirPlantilla(Scanner teclado){
        List<Plantilla> plantillas = JSONPlantilla.leerPlantillas();

        if (plantillas.isEmpty()) {
            System.out.println("No hay plantillas disponibles actualmente.");
            return null;
        }

        System.out.println("----- ELIGE UNA PLANTILLA PARA TU ENTRENAMIENTO -----");
        for (int i = 0; i < plantillas.size(); i++) {
            System.out.println((i+1) + ". " + plantillas.get(i).getNombre());
        }

        System.out.println("Ingrese la plantilla que desea elegir: ");
        int numeroElegido = LecturaTeclado.leerEntero(teclado, 1, plantillas.size());
        Plantilla seleccionada = plantillas.get(numeroElegido-1);

        System.out.println("Has elegido la plantilla: " + seleccionada.getNombre());
        System.out.println("Detalle de la plantilla:");
        seleccionada.mostrarRutina();

        return seleccionada;
    }

    public static Entrenamiento entrenarPorConsola(Scanner teclado){
        Plantilla seleccionada = elegirPlantilla(teclado);
        if(seleccionada==null){
            return null;
        }

        Entrenamiento entrenamiento = new Entrenamiento(seleccionada.getNombre(), new Date());
        List<Serie> seriesDelEntrenamiento = seleccionada.getSeries();

        for (int i = 0; i < seriesDelEntrenamiento.size(); i++) {
            Serie serieActual = seriesDelEntrenamiento.get(i);
            String nombreEjercicio = serieActual.getEjercicio().getNombre();
            int repeticiones = serieActual.getRepeticiones();
            double peso = serieActual.getPeso();

            System.out.printf(nombreEjercicio + ": SERIE "+(i+1)+ "/" + seriesDelEntrenamiento.size());
            System.out.println("Repeticiones sugeridas: " + repeticiones);
            System.out.println("Peso sugerido: " + peso + " kg");

            System.out.println("Desea ajustar el peso (" + peso + " kg) antes de hacer la serie?");

            if(LecturaTeclado.leerBooleanSN(teclado)) {
                peso = LecturaTeclado.leerDouble(teclado, 0.1, 500.0);
                serieActual.setPeso(peso);
                System.out.println("Peso ajustado a " + peso + " kg");
            }

            System.out.println("Desea ajustar las repeticiones ("+repeticiones+")?");
            if(LecturaTeclado.leerBooleanSN(teclado)){
                repeticiones = LecturaTeclado.leerEntero(teclado, 1, 500);
                serieActual.setRepeticiones(repeticiones);
                System.out.println("Repeticiones ");
            }
            System.out.print("Presiona ENTER cuando completes esta serie...");
            teclado.nextLine();


            entrenamiento.agregarSerie(serieActual);

            System.out.println("Serie completada!");
        }
        System.out.println("---ENTRENAMIENTO COMPLETO---");
        entrenamiento.mostrarRutina();
        System.out.println("Fecha de entrenamiento: " + entrenamiento.getFecha());

        return entrenamiento;

    }
}
