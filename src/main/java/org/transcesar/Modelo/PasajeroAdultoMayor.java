package org.transcesar.Modelo;

/**
 * PasajeroAdultoMayor - hereda de Pasajero
 * calcularDescuento() → 0.30 (30% de descuento)
 */
public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.30;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("==== PASAJERO ADULTO MAYOR ====");
        System.out.println("Cedula    : " + getCedula());
        System.out.println("Nombre    : " + getNombre());
        System.out.println("Tipo      : Adulto Mayor");
        System.out.println("Descuento : 30%");
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return getCedula() + ";" + getNombre() + ";AdultoMayor";
    }
}