package org.transcesar.Modelo;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String cedula, String nombre, String fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.15;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("===== PASAJERO ESTUDIANTE =====");
        System.out.println("Cedula           : " + getCedula());
        System.out.println("Nombre           : " + getNombre());
        System.out.println("Fecha nacimiento : " + getFechaNacimiento());
        System.out.println("Tipo             : Estudiante");
        System.out.println("Descuento        : 15%");
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return getCedula() + ";" + getNombre() + ";" + getFechaNacimiento() + ";Estudiante";
    }
}