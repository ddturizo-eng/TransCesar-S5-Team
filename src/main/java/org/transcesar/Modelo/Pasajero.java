package org.transcesar.Modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase abstracta Pasajero — MODIFICADA (Requerimiento 1, Actividad 6)
 * Nuevo atributo: fechaNacimiento (String YYYY-MM-DD)
 * Nuevo método  : esAdultoMayor() → true si edad >= 60 años
 */
public abstract class Pasajero extends Persona {

    private String fechaNacimiento; // formato YYYY-MM-DD

    public Pasajero(String cedula, String nombre, String fechaNacimiento) {
        super(cedula, nombre);
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getter y Setter

    public String getFechaNacimiento(){
        return fechaNacimiento;
    }
    public void   setFechaNacimiento(String f){
        this.fechaNacimiento = f;
    }

    /**
     * Calcula si el pasajero tiene 60 años o más usando LocalDate y Period.
     * @return true si la edad es >= 60 años.
     */
    public boolean esAdultoMayor() {
        try {
            LocalDate nacimiento = LocalDate.parse(fechaNacimiento);
            int edad = Period.between(nacimiento, LocalDate.now()).getYears();
            return edad >= 60;
        } catch (Exception e) {
            System.out.println("Advertencia: fecha de nacimiento inválida → " + fechaNacimiento);
            return false;
        }
    }

    /**
     * Retorna el porcentaje de descuento (0.0 a 1.0).
     * Cada subclase lo redefine (polimorfismo).
     */
    public abstract double calcularDescuento();

    @Override
    public void imprimirDetalle() {
        System.out.println("======== PASAJERO ========");
        System.out.println("Cedula            : " + getCedula());
        System.out.println("Nombre            : " + getNombre());
        System.out.println("Fecha nacimiento  : " + fechaNacimiento);
        System.out.println("Adulto Mayor      : " + (esAdultoMayor() ? "Sí" : "No"));
        System.out.println("Descuento         : " + (int)(calcularDescuento() * 100) + "%");
        System.out.println("==========================");
    }

    @Override
    public String toString() {
        return super.toString() + " | Fecha nac.: " + fechaNacimiento
                + " | Descuento: " + (int)(calcularDescuento() * 100) + "%";
    }
}