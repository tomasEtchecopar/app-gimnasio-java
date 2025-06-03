package Clases.Menu;

import Clases.Usuario.Usuario;

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

    public static Usuario formularioRegistro(Scanner teclado){
        System.out.println("Ingrese nombre de usuario: ");
        String usuario = teclado.nextLine();
        System.out.println("Ingrese contrasenia: ");
        String contrasenia = teclado.nextLine();
        System.out.println("Ingrese su nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese su apellido: ");
        String apellido = teclado.nextLine();
        System.out.println("Ingrese su edad");
        int edad = leerEntero(teclado, 1, 100);
        teclado.nextLine();
        System.out.println("Ingrese su peso (en kg): ");
        double peso = teclado.nextDouble();
        teclado.nextLine();
        System.out.println("Ingrese su altura (en cm): ");
        double altura = teclado.nextDouble();
        teclado.nextLine();
        System.out.println("Es usuario premium? Ingrese s o n");
        boolean premium= leerBooleanSN(teclado);

        return new Usuario(usuario, contrasenia, nombre, apellido, edad, peso, altura, premium);
    }
}
