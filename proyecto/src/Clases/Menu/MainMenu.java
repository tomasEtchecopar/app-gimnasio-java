package Clases.Menu;

import Clases.Lectora;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONPersona;
import org.json.JSONException;

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
            case 2-> registrarse(teclado);
        }
        return true; // para cortar el bucle de run()
    }

    /// REGISTRO
    private static void registrarse(Scanner teclado) throws IllegalArgumentException{
        System.out.println("Bienvenido a nuestra app! A continuacion podrá llenar el formulario de registro:");
        System.out.println("Ingrese nombre de usuario: ");
        String usuario = teclado.nextLine();
        System.out.println("Ingrese contrasenia: ");
        String contrasenia = teclado.nextLine();
        System.out.println("Ingrese su nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese su apellido: ");
        String apellido = teclado.nextLine();
        System.out.println("Ingrese su edad");
        int edad = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Ingrese su peso (en kg): ");
        double peso = teclado.nextDouble();
        teclado.nextLine();
        System.out.println("Ingrese su altura (en cm): ");
        double altura = teclado.nextDouble();
        teclado.nextLine();
        System.out.println("Es usuario premium? Ingrese s o n");
        boolean premium= teclado.nextLine().toLowerCase().charAt(0) == 's';

        try {
            Usuario usuario1 = new Usuario(usuario, contrasenia, nombre, apellido, edad, peso, altura, premium); //instancio el usuario
            JSONPersona.agregarUsuario(usuario1); // lo agrego al json
        } catch (IllegalArgumentException | JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
