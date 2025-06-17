package Clases.Menu.Admin;

import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Menu.Interfaces.MenuUsuario;
import Clases.Usuario.Persona;


import java.util.Scanner;

public class AdminMenu implements MenuUsuario {

    @Override
    public void mostrar(Scanner teclado, Persona usuario) {
        int opcion=-1;
        while (opcion != 4) {
            System.out.println("=============================");
            System.out.println("\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido, ENTRENADOR!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Menu ejercicios");
            System.out.println("2) Menu rutinas");
            System.out.println("3) Menu usuarios");
            System.out.println("4) Cerrar sesion");

            opcion = this.elegirOpcion(teclado, usuario);
            menuCaller(teclado, opcion, usuario);
            LecturaTeclado.continuar(teclado);
        }

    }

    @Override
    public int elegirOpcion(Scanner teclado, Persona usuario) {
        return LecturaTeclado.leerEntero(teclado, 1, 4);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion, Persona usuario) {
        switch(opcion){
            case 1 -> MenuEjerciciosAdmin.menuEjercicios(teclado);
            case 2 ->MenuRutinasAdmin.menuRutinas(teclado);
            case 3 -> MenuUsuariosAdmin.menuUsuarios(teclado);
            case 4 -> System.out.printf("Adios!\n\n");
        }
    }
    }



