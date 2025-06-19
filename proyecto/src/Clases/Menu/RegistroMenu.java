package Clases.Menu;

import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONUsuario;

import java.util.Scanner;

public class RegistroMenu{

    public static void mostrar(Scanner teclado) throws IllegalArgumentException{
        System.out.println("Bienvenido a nuestra app! A continuacion podrá llenar el formulario de registro:");
        JSONUsuario.registro(formularioRegistro(teclado));
    }


    public static Usuario formularioRegistro(Scanner teclado){
        System.out.println("Ingrese nombre de usuario: ");
        String usuario = teclado.nextLine();
        System.out.println("Ingrese contrasenia: ");
        String contrasenia = teclado.nextLine();
        System.out.println("Ingrese su nombre: ");
        String nombre = teclado.nextLine();
        nombre = capitalizarPrimeraLetra(nombre);
        System.out.println("Ingrese su apellido: ");
        String apellido = teclado.nextLine();
        apellido=capitalizarPrimeraLetra(apellido);
        System.out.println("Ingrese su edad");
        int edad = LecturaTeclado.leerEntero(teclado, 1, 100);
        System.out.println("Ingrese su peso (en kg): ");
        double peso = teclado.nextDouble();
        teclado.nextLine();
        System.out.println("Ingrese su altura (en cm): ");
        double altura = teclado.nextDouble();
        teclado.nextLine();

        return new Usuario(usuario, contrasenia, nombre, apellido, edad, peso, altura);
    }

    private static String capitalizarPrimeraLetra(String texto){
        if(texto==null || texto.isEmpty()){
            return texto;
        }
        return texto.substring(0, 1).toUpperCase()+ texto.substring(1).toLowerCase();
    }
}
