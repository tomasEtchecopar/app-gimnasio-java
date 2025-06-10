package Clases.Menu.Utiles;

import Clases.Gimnasio.Ejercicio;
import Clases.Menu.MainMenu;
import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONEjercicio;
import Clases.manejoJSON.JSONPersona;

import java.util.Map;
import java.util.Scanner;

public class Editores {

    public static void editarEjercicio(  Scanner teclado, String nombre){
        Map<String, Ejercicio> ejercicios = JSONEjercicio.leerEjercicios();
        int opcion;

        for(Map.Entry<String, Ejercicio> ejercicioEntry: ejercicios.entrySet()){
            if(ejercicioEntry.getValue().getNombre().equals(nombre)){
                System.out.println("\n Que desea editar?\n1. Nombre\n2. Descripcion\n");
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion){
                    case 1 -> {
                        System.out.println("\nIngrese el nuevo nombre: ");
                        ejercicioEntry.getValue().setNombre(teclado.nextLine());
                    }
                    case 2 -> {
                        System.out.println("\nIngrese la nueva descripcion: ");
                        ejercicioEntry.getValue().setNombre(teclado.nextLine());
                    }
                }

            }
        }
        JSONEjercicio.editarEjercicio(ejercicios);
    }
    public static void menuEdicionDeUsuario(Scanner teclado, Persona usuario){
        int opcion=-1;
        while(opcion!=6){
            MainMenu.limpiarConsola();
            System.out.println("Ingrese que campo desea editar: ");
            System.out.println("1) Nombre");
            System.out.println("2) Apellido");
            System.out.println("3) Edad");
            System.out.println("4) Peso");
            System.out.println("5) Altura");
            System.out.println("6) Volver");
            opcion = LecturaTeclado.leerEntero(teclado, 1, 6);

            switch(opcion){
                case 1 ->{
                    System.out.println("Ingrese nuevo nombre: ");
                    usuario.setNombre(teclado.nextLine());
                    JSONPersona.editarUsuario((Usuario) usuario);
                }
            }
        }
    }

}
