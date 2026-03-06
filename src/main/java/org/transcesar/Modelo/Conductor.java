package org.transcesar.Modelo;

/**
 * Clase Conductor - hereda de Persona
 */
public class Conductor extends Persona {

    private String numeroLicencia;
    private String categoriaLicencia; // B1 | B2 | C1 | C2

    public Conductor(String cedula, String nombre, String numeroLicencia, String categoriaLicencia) {
        super(cedula, nombre);
        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("======== CONDUCTOR ========");
        System.out.println("Cedula          : " + getCedula());
        System.out.println("Nombre          : " + getNombre());
        System.out.println("Num. Licencia   : " + numeroLicencia);
        System.out.println("Cat. Licencia   : " + categoriaLicencia);
        System.out.println("===========================");
    }

    @Override
    public String toString() {
        return super.toString() + " | Licencia: " + numeroLicencia + " (" + categoriaLicencia + ")";
    }
}