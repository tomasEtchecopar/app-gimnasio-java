package Clases.Menu.Admin;

import Clases.Menu.Usuario.UsuarioMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Menu.Interfaces.MenuAcciones;
import Clases.Usuario.Usuario;


import java.util.Scanner;

public class AdminMenu implements MenuAcciones {

    @Override
    public void mostrar(Scanner teclado, Usuario usuario) {
        int opcion=-1;
        while (opcion != 5) {
            System.out.println("=============================");
            System.out.println("\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido, ENTRENADOR!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Menu ejercicios");
            System.out.println("2) Menu rutinas");
            System.out.println("3) Menu usuarios");
            System.out.println("4) Acceder a mi menú de entrenamiento");
            System.out.println("5) Cerrar sesion");

            opcion = this.elegirOpcion(teclado, usuario);
            menuCaller(teclado, opcion, usuario);
            LecturaTeclado.continuar(teclado);
        }

    }

    @Override
    public int elegirOpcion(Scanner teclado, Usuario usuario) {
        return LecturaTeclado.leerEntero(teclado, 1, 5);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion, Usuario usuario) {
        switch(opcion){
            case 1 -> MenuEjerciciosAdmin.menuEjercicios(teclado);
            case 2 ->MenuRutinasAdmin.menuRutinas(teclado);
            case 3 -> MenuUsuariosAdmin.menuUsuarios(teclado, usuario);
            case 4->   new UsuarioMenu().mostrar(teclado, usuario);
            case 5 ->{
                return;
            }
        }
    }
    }



