package Clases.Menu.Usuario;

import Clases.Menu.Interfaces.MenuUsuario;
import Clases.Menu.MainMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Persona;
import Clases.Menu.Utiles.Editores;

import java.util.Scanner;

public class UsuarioMenu implements MenuUsuario {
    @Override
    public void mostrar(Scanner teclado, Persona usuario) {
        int opcion = -1;
        while (opcion != 6) {
            MainMenu.limpiarConsola();
            System.out.println("=============================");
            System.out.println("\tAPP DE ENTRENAMIENTO ");
            System.out.println("=============================");
            System.out.println("Bienvenido!");
            System.out.println("¿Qué te gustaría hacer hoy?");
            System.out.println("1) Empezar entrenamiento");
            System.out.println("2) Ver mi historial de entrenamientos");
            System.out.println("3) Ver estadisticas");
            System.out.println("4) Ver informacion personal");
            System.out.println("5) Actualizar informacion personal");
            System.out.println("6) Cerrar sesion");
            opcion = this.elegirOpcion(teclado, usuario);
            menuCaller(teclado, opcion, usuario);
        }
    }

    @Override
    public int elegirOpcion(Scanner teclado, Persona usuario) {
        return LecturaTeclado.leerEntero(teclado, 1, 6);
    }

    @Override
    public void menuCaller(Scanner teclado, int opcion, Persona usuario) {
        MainMenu.limpiarConsola();
        switch (opcion) {
            case 1 -> {
                usuario.entrenar(teclado);
            }
            case 2 -> usuario.verHistorial();
            case 3 -> mostrarEstadisticas(usuario);
            case 4 -> System.out.println(usuario);
            case 5 -> Editores.menuEdicionDeUsuario(teclado, usuario);
        }

        System.out.println("Si desea continuar presione ENTER");
        teclado.nextLine();
    }

    public static void mostrarEstadisticas(Persona usuario) {
        System.out.println("--Estadisticas de " + usuario.getNombre());
        System.out.println("Edad: " + usuario.getEdad());
        System.out.println("Peso: " + usuario.getPeso());
        System.out.println("Altura: " + usuario.getAltura());
        if (usuario.getIMC() == 0) {
            System.out.println("Para acceder a su indice de masa corporal necesita ser un usuario premium");
        } else {
            System.out.println("Indice de masa corporal: " + usuario.getIMC());
            switch (usuario.tieneSobrepeso()) {
                case 1 -> System.out.println("Sobrepeso");
                case 0 -> System.out.println("Peso ideal");
                case -1 -> System.out.println("Peso bajo");
            }
        }
    }
}
