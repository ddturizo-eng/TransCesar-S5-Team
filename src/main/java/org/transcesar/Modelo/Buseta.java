package org.transcesar.Modelo;

public class Buseta extends Vehiculo {
    public Buseta(String placa, String ruta) {
        super(placa, ruta, 19, 8000.0);
    }
    @Override
    public void imprimirDetalle() {
        System.out.println("[BUSETA] Placa: " + getPlaca()
                + " | Ruta: " + getRuta()
                + " | Tarifa: $" + getTarifaBase()
                + " | Cupos: " + getPasajerosActuales() + "/" + getCapacidadMaxima());
    }
    @Override
    public String toString() {
        return "Buseta;" + getPlaca() + ";" + getRuta()
                + ";" + getCapacidadMaxima() + ";" + getPasajerosActuales()
                + ";" + getTarifaBase();
    }
}
