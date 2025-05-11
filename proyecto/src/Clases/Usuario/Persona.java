package Clases.Usuario;

public abstract class Persona {
    private String nombreApellido;
    private String mail;
    private String contrasenia;
    private int edad;
    private double peso;
    private int alturaCM;
    private boolean premium;

    //  Constructor de la clase Persona para Usuarios

    public Persona(String nombreApellido, String mail, String contrasenia, int edad, double peso, int alturaCM, boolean premium) {
        this.nombreApellido = nombreApellido;
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.edad = edad;
        this.peso = peso;
        this.alturaCM = alturaCM;
        this.premium = premium;
    }

    // Constructor de la clase Persona para Administradores. Al usar solo estos parametros dentro del constructor, se entiende de que se trata de un administrador.

    public Persona(String nombreApellido, String mail, String contrasenia){
        this.nombreApellido = nombreApellido;
        this.mail = mail;
        this.contrasenia = contrasenia;
    }

    //  Getters y Setters de los parámetros

    public String getNombreApellido () {
        return nombreApellido;
    }

    public void setNombreApellido (String nombreApellido){
        this.nombreApellido = nombreApellido;
    }

    public String getMail () {
        return mail;
    }

    public void setMail (String mail){
        this.mail = mail;
    }

    public int getEdad () {
        return edad;
    }

    public void setEdad ( int edad){
        this.edad = edad;
    }

    public double getPeso () {
        return peso;
    }

    public void setPeso ( double peso){
        this.peso = peso;
    }

    public int getAlturaCM () {
        return alturaCM;
    }

    public void setAlturaCM ( int alturaCM){
        this.alturaCM = alturaCM;
    }

    public boolean isPremium () {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    //Implemetar en subclases:

    public abstract void borrarUsuario();
    public abstract void editarUsuario();

}
