package org.transcesar.Modelo;

public class Bus extends Vehiculo {
    public Bus(String placa, Ruta ruta) {
        super(placa, ruta, 45, 15000.0);
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("[BUS] Placa: " + getPlaca()
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