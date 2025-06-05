package Clases.Usuario;

import Clases.Acciones;
import Clases.Gimnasio.Entrenamiento;

import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class Persona implements Acciones {
    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private int edad;
    private double peso; // en kg
    private double altura; // en cm
    private List<Entrenamiento> historial;
    boolean premium;

    // Constructores.
    public Persona(String usuario, String contrasenia, String nombre, String apellido, int edad, double peso, double altura, boolean premium) throws IllegalArgumentException {
        if(usuario==null || contrasenia ==null){
            throw new IllegalArgumentException("Usuario o contrasenia vacios");
        }
        if(edad%1 !=0){
            throw new IllegalArgumentException("La edad debe ser un numero entero");
        }
        if(edad<0 ||peso<0 || altura<0){
            throw new IllegalArgumentException("Ninguno de los campos numericos pueden ser negativos");
        }
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.premium = premium;
        this.historial = new ArrayList<>();
    }

    public Persona(String usuario, String contrasenia, String nombre, String apellido, int edad, double peso, double altura, boolean premium, List<Entrenamiento> historial) throws IllegalArgumentException {
        if(usuario==null || contrasenia ==null){
            throw new IllegalArgumentException("Usuario o contrasenia vacios");
        }
        if(edad%1 !=0){
            throw new IllegalArgumentException("La edad debe ser un numero entero");
        }
        if(edad<0 ||peso<0 || altura<0){
            throw new IllegalArgumentException("Ninguno de los campos numericos pueden ser negativos");
        }
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.premium = premium;
        this.historial = historial;
    }

    public Persona(String usuario, String contrasenia) throws IllegalArgumentException{
        if(usuario==null || contrasenia ==null){
            throw new IllegalArgumentException("Usuario o contrasenia vacios");
        }
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Persona() {
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Entrenamiento> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Entrenamiento> historial) {
        this.historial = historial;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double getIMC(){
        return this.isPremium()? this.getPeso()/(this.getAltura()/100) : 0;
    }
    //manejo de historial
    public void agregarEntrenamiento(Entrenamiento entrenamiento){
        this.historial.add(entrenamiento);
    }


    @Override
    public void verHistorial() {
        List<Entrenamiento> lista = this.getHistorial();
        if (lista.isEmpty() || lista==null) {
            System.out.println("El historial esta vacio");
            return;
        }
        for (Entrenamiento ent : lista) {
            System.out.println("------------------------------");
            ent.mostrarRutina();
        }
    }


    //se compara unicamente el usuario.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(usuario, persona.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(usuario);
    }

    @Override
    public java.lang.String toString() {
        return "Persona{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", altura=" + altura +
                '}';
    }

}