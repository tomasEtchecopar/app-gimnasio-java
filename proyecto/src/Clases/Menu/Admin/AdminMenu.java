package Clases.Menu.Admin;

import Clases.Gimnasio.Ejercicio;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Menu.Interfaces.MenuUsuario;
import Clases.manejoJSON.JSONEjercicio;
import org.json.JSONException;
import Clases.Menu.Admin.MenuEjerciciosAdmin;

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

            opcion = teclado.nextInt();
            teclado.nextLine();

            menuCaller(teclado, opcion);
        }
    }

    @Override
    public int elegirOpcion(Scanner teclado) {
        return LecturaTeclado.leerEntero(teclado, 1, 4);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion) {
        switch(opcion){
            case 1 -> MenuEjerciciosAdmin.menuEjercicios(teclado);
        }
    }


    /// FUNCIONALIDADES EXCLUSIVAS DEL ADMIN


}
