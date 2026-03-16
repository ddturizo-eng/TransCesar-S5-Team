package org.transcesar.Modelo;

public class MicroBus extends Vehiculo {
    public MicroBus(String placa, Ruta ruta) {
        super(placa, ruta, 25, 10000.0);
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("[MICROBUS] Placa: " + getPlaca()
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