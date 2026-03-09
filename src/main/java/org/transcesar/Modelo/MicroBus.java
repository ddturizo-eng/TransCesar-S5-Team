package org.transcesar.Modelo;

public class MicroBus extends Vehiculo {
    public MicroBus(String placa, String ruta) {
        super(placa, ruta, 25, 10000.0);
    }
    @Override public void imprimirDetalle() {
        System.out.println("[MICROBUS] Placa: " + getPlaca()
                + " | Ruta: " + getRuta() + " | Tarifa: $" + getTarifaBase());
    }
    @Override public String toString() {
        return "MicroBus;" + getPlaca() + ";" + getRuta()
                + ";" + getCapacidadMaxima() + ";" + getPasajerosActuales()
                + ";" + getTarifaBase();
    }
}
