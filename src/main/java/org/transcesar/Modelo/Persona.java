package org.transcesar.Modelo;

/**
 * Clase abstracta Persona
 */
public abstract class Persona implements Imprimible {

    private String cedula;
    private String nombre;

    public Persona(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public abstract void imprimirDetalle();

    @Override
    public String toString() {
        return "Cedula: " + cedula + " | Nombre: " + nombre;
    }
}