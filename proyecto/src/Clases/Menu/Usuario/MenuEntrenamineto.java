package Clases.Menu.Usuario;

import Clases.Gimnasio.Plantilla;
import Clases.Menu.Interfaces.MenuUsuario;
import Clases.Menu.Utiles.LecturaTeclado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEntrenamineto implements MenuUsuario {
    @Override
    public void mostrar(Scanner teclado){
        System.out.println("1) Elegir plantilla predeterminada");
        System.out.println("2) Crear rutina");
    }

    @Override
    public int elegirOpcion(Scanner teclado) {
        return LecturaTeclado.leerEntero(teclado, 1, 2);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion) {

    }

    private void entrenamientoDesdePlantilla(Scanner teclado){
        List<Plantilla> plantillas = new ArrayList<>(); //TODO: lectura y escritura del json plantillas
        System.out.println("Seleccione una plantilla");
        for (int i = 0; i < plantillas.size(); i++) {
            Plantilla p = plantillas.get(i);
            System.out.println((i+1) +""+ p);
        }


    }
}
