package Clases.Menu;

import Clases.Lectora;

import java.util.Scanner;

public class MainMenu {

    //
    public static void run(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido a nuestra app de entrenamineto");
        try{
            elegirLogIn(teclado);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }finally {
            teclado.close();
        }
    }

    /// LOG IN
    private static void elegirLogIn(Scanner teclado)throws IllegalArgumentException{
        System.out.println("1. Iniciar Sesion.");
        System.out.println("2. Registrarse");
        int opcion = teclado.nextInt();
        teclado.nextLine();
        if(opcion<0 || opcion >1){
            throw new IllegalArgumentException("Opcion incorrecta. Debe ingresar 1 o 2.");
        }

    }
}
