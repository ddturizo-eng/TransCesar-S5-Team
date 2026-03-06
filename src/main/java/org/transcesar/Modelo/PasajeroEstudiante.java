package org.transcesar.Modelo;

/**
 * PasajeroEstudiante - hereda de Pasajero
 * calcularDescuento() → 0.15 (15% de descuento)
 */
public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.15;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("===== PASAJERO ESTUDIANTE =====");
        System.out.println("Cedula    : " + getCedula());
        System.out.println("Nombre    : " + getNombre());
        System.out.println("Tipo      : Estudiante");
        System.out.println("Descuento : 15%");
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return getCedula() + ";" + getNombre() + ";Estudiante";
    }
}