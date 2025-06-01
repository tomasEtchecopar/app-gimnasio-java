package Clases.Menu;

import Clases.Lectora;
import Clases.Usuario.Admin;
import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONPersona;
import Excepciones.UsuarioNoExisteException;
import org.json.JSONException;

import java.io.FileNotFoundException;
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
        System.out.println("3. Salir");
        int opcion = teclado.nextInt();
        teclado.nextLine();
        if(opcion<1 || opcion >3){
            throw new IllegalArgumentException("Opcion incorrecta. Debe ingresar 1 o 2.");
        }
        switch(opcion){
            case 1 -> logIn(teclado);
            case 2-> registrarse(teclado);
            case 3 -> {
                return true; // para cortar el bucle de run()
            }
        }
        return false;
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
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    //LOG IN
    private static void logIn(Scanner teclado){
        Persona usuario = null;
        System.out.println("Ingrese nombre de usuario: ");
        String nombreUsuario = teclado.nextLine();
        System.out.println("Ingrese contrasenia: ");
        String contrasenia = teclado.nextLine();


        try {
            usuario = JSONPersona.getUsuarioFromJSON(new Usuario(nombreUsuario, contrasenia));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(usuario);


    }


}
