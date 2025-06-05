package Clases.Menu.Utiles;

import java.util.Scanner;

public class LecturaTeclado {

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

    public static boolean leerBooleanSN(Scanner teclado) {
        String linea;
        do {
            linea = String.valueOf(teclado.nextLine().charAt(0)).toLowerCase();
        } while (!linea.equals("s") && !linea.equals("n"));

        return linea.equals("s");
    }
    public static double leerDouble(Scanner teclado, double min, double max){
        double opcion;
        do{
            while(!teclado.hasNextDouble()){
                System.out.println("Debe ingresar un número valido.");
                teclado.next();
            }
            opcion = teclado.nextDouble();
            teclado.nextLine();
        }while(opcion<min || opcion > max);
        return opcion;
    }


}
