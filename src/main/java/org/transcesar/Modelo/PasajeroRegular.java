package org.transcesar.Modelo;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre, String fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("======= PASAJERO REGULAR =======");
        System.out.println("Cedula           : " + getCedula());
        System.out.println("Nombre           : " + getNombre());
        System.out.println("Fecha nacimiento : " + getFechaNacimiento());
        System.out.println("Tipo             : Regular");
        System.out.println("Descuento        : 0%");
        System.out.println("================================");
    }

    @Override
    public String toString() {
        return getCedula() + ";" + getNombre() + ";" + getFechaNacimiento() + ";Regular";
    }
}