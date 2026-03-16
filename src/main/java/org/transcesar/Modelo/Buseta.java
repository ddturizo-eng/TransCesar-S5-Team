package org.transcesar.Modelo;

public class Buseta extends Vehiculo {
    public Buseta(String placa, Ruta ruta) {
        super(placa, ruta, 19, 8000.0);
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("[BUSETA] Placa: " + getPlaca()
                + " | Ruta: " + (getRuta() != null ? getRuta().getCodigoRuta() : "Sin ruta")
                + " | Tarifa: $" + getTarifaBase()
                + " | Cupos: " + getPasajerosActuales() + "/" + getCapacidadMaxima());
    }

    @Override
    public String toString() {
        return getPlaca() + ";" + (getRuta() != null ? getRuta().getCodigoRuta() : "SIN_RUTA")
                + ";" + getCapacidadMaxima() + ";" + getPasajerosActuales()
                + ";" + getTarifaBase();
    }
}