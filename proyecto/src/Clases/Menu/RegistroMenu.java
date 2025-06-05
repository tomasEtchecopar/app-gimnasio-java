package Clases.Menu;

import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONPersona;
import org.json.JSONException;

import java.util.Scanner;

public class RegistroMenu{

    public static void mostrar(Scanner teclado) throws IllegalArgumentException{
        System.out.println("Bienvenido a nuestra app! A continuacion podrá llenar el formulario de registro:");
        Usuario usuario = formularioRegistro(teclado);
        try {
            JSONPersona.escribirJSON(usuario); // lo agrego al json
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
        int edad = LecturaTeclado.leerEntero(teclado, 1, 100);
        System.out.println("Ingrese su peso (en kg): ");
        double peso = teclado.nextDouble();
        teclado.nextLine();
        System.out.println("Ingrese su altura (en cm): ");
        double altura = teclado.nextDouble();
        teclado.nextLine();
        System.out.println("Es usuario premium? Ingrese s o n");
        boolean premium= LecturaTeclado.leerBooleanSN(teclado);

        return new Usuario(usuario, contrasenia, nombre, apellido, edad, peso, altura, premium);
    }
}
