package Clases.Menu;

import Clases.Gimnasio.Ejercicio;
import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONEjercicio;
import Clases.manejoJSON.JSONPersona;
import org.json.JSONException;

import java.util.Map;
import java.util.Scanner;

public class MainMenu {

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
        int opcion=-1;

        opcion=leerEntero(teclado, 1, 3);

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

        try {
            Usuario usuario1 = formularioRegistro(teclado); //instancio el usuario
            JSONPersona.escribirJSON(usuario1); // lo agrego al json
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Usuario formularioRegistro(Scanner teclado){
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
        boolean premium= teclado.nextLine().toLowerCase().charAt(0) == 's';

        return new Usuario(usuario, contrasenia, nombre, apellido, edad, peso, altura, premium);
    }

    //LOG IN
    private static void logIn(Scanner teclado){
        Persona usuario = null;
        System.out.println("Ingrese nombre de usuario: ");
        String nombreUsuario = teclado.nextLine();
        System.out.println("Ingrese contrasenia: ");
        String contrasenia = teclado.nextLine();


        try {
            usuario = JSONPersona.getFromJSON(new Usuario(nombreUsuario, contrasenia));
            mainMenu(teclado, usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    private static void mainMenu(Scanner teclado, Persona usuario){
        if(usuario.getUsuario().equalsIgnoreCase("admin")){
           menuAdmin(teclado, usuario);
        }else{
            System.out.println("=============================");
            System.out.println("\t\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido, " + usuario.getNombre() +"!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Empezar entrenamiento");
            System.out.println("2) Ver mi semana");
            System.out.println("3) Ver mi historial de entrenamientos");
            System.out.println("4) Ver estadisticas");
            System.out.println("5) Actualizar informacion personal");
            System.out.println("6) Cerrar sesion");
        }
    }

    private static void menuAdmin(Scanner teclado, Persona usuario) {
        int opcion = -1;
        while (opcion != 6) {
            System.out.println("=============================");
            System.out.println("\t\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido, " + usuario.getNombre() + "!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Menu ejercicios");
            System.out.println("2) Menu rutinas");
            System.out.println("3) ");
            System.out.println("4) Ver usuarios");
            System.out.println("5) Actualizar informacion personal");
            System.out.println("6) Cerrar sesion");

            opcion = leerEntero(teclado, 1, 6);
            switch (opcion) {
                case 1 -> menuEjercicios(teclado);
            }
        }
    }

    private static void menuEjercicios(Scanner teclado){
        int opcion=-1;
        while(opcion!=5){
            System.out.println("--EJERCICIOS--");
            System.out.println("1) Ver ejercicios");
            System.out.println("2) Agregar Ejercicio");
            System.out.println("3) Borrar Ejercicio");
            System.out.println("4) Editar Ejercicio");
            System.out.println("5) Salir");

            opcion = leerEntero(teclado, 1, 5);
            switch(opcion){
                case 1 -> mostrarEjercicios();
                case 2 -> {
                    try {
                        JSONEjercicio.escribirJSON(cargarEjercicio(teclado));
                    } catch (JSONException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static void mostrarEjercicios(){
        try {
            Map<String, Ejercicio> ejercicios = JSONEjercicio.getFromJSON();
            for(Ejercicio ej : ejercicios.values()){
                System.out.println("--------------------\n");
                System.out.println("Nombre: " + ej.getNombre());
                System.out.println("Descripcion: " +ej.getDescripcion());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    private static Ejercicio cargarEjercicio(Scanner teclado){
        System.out.println("Ingrese el nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese la descripción: ");
        String descripcion = teclado.nextLine();
        return new Ejercicio(nombre, descripcion);
    }


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


}
