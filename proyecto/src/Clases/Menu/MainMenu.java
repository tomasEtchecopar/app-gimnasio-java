package Clases.Menu;

import Clases.Lectora;

import java.util.Scanner;

public class MainMenu {

    //
    public static void run(){
        boolean flag=false;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido a nuestra app de entrenamineto");
        while(!flag){
            try{
                flag = elegirLogIn(teclado);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        teclado.close();
    }

    /// LOG IN
    private static boolean elegirLogIn(Scanner teclado)throws IllegalArgumentException{
        System.out.println("1. Iniciar Sesion.");
        System.out.println("2. Registrarse");
        int opcion = teclado.nextInt();
        teclado.nextLine();
        if(opcion<1 || opcion >2){
            throw new IllegalArgumentException("Opcion incorrecta. Debe ingresar 1 o 2.");
        }
        switch(opcion){
            case 1->
        }
        return true; // para cortar el bucle de run()
    }

    /// REGISTRO
    private static void registrarse(Scanner teclado) throws IllegalArgumentException{
        System.out.println("Bienvenido a nuestra app! A continuacion podrá llenar el formulario de registro:");
        System.out.println("Ingrese nombre de usuario: ");

    }
}
