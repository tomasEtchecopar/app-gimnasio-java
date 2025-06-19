package Clases.Menu.Interfaces;

import Clases.Usuario.Usuario;

import java.util.Scanner;

public interface MenuAcciones {
    void mostrar(Scanner teclado, Usuario usuario);
    int elegirOpcion(Scanner teclado, Usuario usuario);
    void menuCaller(Scanner teclado, int opcion, Usuario usuario);
}
