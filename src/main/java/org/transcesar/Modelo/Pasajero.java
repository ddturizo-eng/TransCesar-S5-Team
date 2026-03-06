package org.transcesar.Modelo;

/**
 * Clase abstracta Pasajero - hereda de Persona
 * Define el metodo abstracto calcularDescuento() → polimorfismo.
 * Hereda cedula y nombre de Persona.
 */
public abstract class Pasajero extends Persona {

    public Pasajero(String cedula, String nombre) {
        super(cedula, nombre);
    }

    /**
     * Retorna el porcentaje de descuento del pasajero (0.0 - 1.0).
     * Cada subclase lo redefine (polimorfismo).
     */
    public abstract double calcularDescuento();

    @Override
    public void imprimirDetalle() {
        System.out.println("======== PASAJERO ========");
        System.out.println("Cedula    : " + getCedula());
        System.out.println("Nombre    : " + getNombre());
        System.out.println("Descuento : " + (int)(calcularDescuento() * 100) + "%");
        System.out.println("==========================");
    }

    @Override
    public String toString() {
        return super.toString() + " | Descuento: " + (int)(calcularDescuento() * 100) + "%";
    }
}