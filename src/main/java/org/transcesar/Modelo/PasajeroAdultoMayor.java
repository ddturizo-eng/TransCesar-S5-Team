package org.transcesar.Modelo;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String cedula, String nombre, String fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.30;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("==== PASAJERO ADULTO MAYOR ====");
        System.out.println("Cedula           : " + getCedula());
        System.out.println("Nombre           : " + getNombre());
        System.out.println("Fecha nacimiento : " + getFechaNacimiento());
        System.out.println("Tipo             : Adulto Mayor");
        System.out.println("Descuento        : 30%");
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return getCedula() + ";" + getNombre() + ";" + getFechaNacimiento() + ";AdultoMayor";
    }
}