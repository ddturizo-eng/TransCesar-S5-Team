package org.transcesar.Modelo;

public class Bus extends Vehiculo {
    public Bus(String placa, String ruta) {
        super(placa, ruta, 45, 15000.0);
    }
    @Override public void imprimirDetalle() {
        System.out.println("[BUS] Placa: " + getPlaca()
                + " | Ruta: " + getRuta() + " | Tarifa: $" + getTarifaBase());
    }
    @Override public String toString() {
        return "Bus;" + getPlaca() + ";" + getRuta()
                + ";" + getCapacidadMaxima() + ";" + getPasajerosActuales()
                + ";" + getTarifaBase();
    }
}
