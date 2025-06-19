package Clases.Menu.Utiles;

import Clases.Gimnasio.Ejercicio;
import Clases.Menu.MainMenu;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONEjercicio;
import Clases.manejoJSON.JSONUsuario;

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
                        ejercicioEntry.getValue().setDescripcion(teclado.nextLine());
                    }
                }

            }
        }
        JSONEjercicio.editarEjercicio(ejercicios);
    }
    public static void menuEdicionDeUsuario(Scanner teclado, Usuario usuario){
        int opcion=-1;
        while(opcion!=6){
            MainMenu.limpiarConsola();
            System.out.println("Ingrese que campo desea editar: ");
            System.out.println("1) Nombre");
            System.out.println("2) Apellido");
            System.out.println("3) Edad");
            System.out.println("4) Peso");
            System.out.println("5) Altura");
            System.out.println("6) Nombre de Usuario");
            System.out.println("7) Contraseña");
            System.out.println("8) Volver");
            opcion = LecturaTeclado.leerEntero(teclado, 1, 8);

            switch(opcion){
                case 1 ->{
                    System.out.println("Ingrese nuevo nombre: ");
                    usuario.setNombre(teclado.nextLine());
                }
                case 2 ->{
                    System.out.println("Ingrese nuevo apellido: ");
                    usuario.setApellido(teclado.nextLine());
                }
                case 3->{
                    System.out.println("Ingrese nueva edad: ");
                    usuario.setEdad(LecturaTeclado.leerEntero(teclado, 0, 100));
                }
                case 4->{
                    System.out.println("Ingrese nuevo peso (kg): ");
                    usuario.setPeso(LecturaTeclado.leerDouble(teclado, 0, 500));
                }
                case 5 ->{
                    System.out.println("Ingrese nueva altura (cm): ");
                    usuario.setAltura(LecturaTeclado.leerDouble(teclado, 0, 250));
                }
                case 6 ->{
                    System.out.println("Ingrese nuevo nombre de usuario: ");
                    usuario.setUsuario(teclado.nextLine());
                }
                case 7 ->{
                    System.out.println("Ingrese la nueva contraseña: ");
                    usuario.setContrasenia(teclado.nextLine());
                }
                case 8->{
                    return;
                }
            }
            JSONUsuario.actualizarUsuario(usuario);
        }
    }


}
