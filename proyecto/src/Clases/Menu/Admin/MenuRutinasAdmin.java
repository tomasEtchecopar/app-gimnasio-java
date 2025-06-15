package Clases.Menu.Admin;

import Clases.Gimnasio.Plantilla;
import Clases.Gimnasio.Rutina;
import Clases.Menu.MainMenu;

import java.util.Scanner;

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
            switch(opcion){
                //case 1 ->
            }
        }
    }

    /*private static Rutina cargarRutinaPorTeclado(Scanner teclado){
        String nombre;
        System.out.println("Ingrese un nombre para la rutina: ");
    }*/
}

