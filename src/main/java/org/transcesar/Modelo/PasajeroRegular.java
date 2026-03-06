package org.transcesar.Modelo;

/**
 * PasajeroRegular - hereda de Pasajero
 * calcularDescuento() → 0.0 (sin descuento)
 */
public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("======= PASAJERO REGULAR =======");
        System.out.println("Cedula    : " + getCedula());
        System.out.println("Nombre    : " + getNombre());
        System.out.println("Tipo      : Regular");
        System.out.println("Descuento : 0%");
        System.out.println("================================");
    }

    @Override
    public String toString() {
        return getCedula() + ";" + getNombre() + ";Regular";
    }
}