package Clases.Menu.Interfaces;

import Clases.Usuario.Persona;

import java.util.Scanner;

public interface MenuUsuario {
    void mostrar(Scanner teclado, Persona usuario);
    int elegirOpcion(Scanner teclado, Persona usuario);
    void menuCaller(Scanner teclado, int opcion, Persona usuario);
}
