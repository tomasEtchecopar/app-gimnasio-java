package Clases.Menu.Interfaces;

import java.util.Scanner;

public interface MenuUsuario {
    void mostrar(Scanner teclado);
    int elegirOpcion(Scanner teclado);
    void menuCaller(Scanner teclado, int opcion);
}
