package Clases.Menu.Utiles;

import java.util.Scanner;

public class LecturaTeclado {

    // Funcion para corroborar que los enteros ingresados sean dentro del limite de opciones (por ejemplo, de 1 a 4 opciones.
    // Esto asegura que no se ingrese un dato fuera del rango de opciones.
    public static int leerEntero(Scanner teclado, int min, int max) {
        int opcion;
        do {
            while (!teclado.hasNextInt()) {
                System.out.println("Debe ingresar un número válido.");
                teclado.next();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();
        } while (opcion < min || opcion > max);
        return opcion;
    }


    // Funcion para corroborar que no se ingrese una opcion incorrecta a una pregunta "s / n".
    public static boolean leerBooleanSN(Scanner teclado) {
        String linea;
        do {
            linea = String.valueOf(teclado.nextLine().charAt(0)).toLowerCase();
        } while (!linea.equals("s") && !linea.equals("n"));

        return linea.equals("s");
    }


}
