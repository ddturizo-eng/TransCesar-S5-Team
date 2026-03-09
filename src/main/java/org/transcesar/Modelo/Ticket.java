package org.transcesar.Modelo;

/**
 * Clase Ticket - implementa Calculable e Imprimible
 * Representa la compra de un tiquete de viaje.
 * valorFinal = tarifaBase * (1 - pasajero.calcularDescuento())
 */
public class Ticket implements Calculable, Imprimible {

    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private String fechaCompra;
    private String origen;
    private String destino;
    private double valorFinal;

    public Ticket(Pasajero pasajero, Vehiculo vehiculo, String fechaCompra, String origen, String destino) {
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = fechaCompra;
        this.origen = origen;
        this.destino = destino;
        // Calcula el valor final aplicando el descuento del pasajero
        this.valorFinal = vehiculo.getTarifaBase() * (1 - pasajero.calcularDescuento());
    }

    // Getters
    public Pasajero getPasajero()    { return pasajero; }
    public Vehiculo getVehiculo()    { return vehiculo; }
    public String getFechaCompra()   { return fechaCompra; }
    public String getOrigen()        { return origen; }
    public String getDestino()       { return destino; }
    public double getValorFinal()    { return valorFinal; }

    // Calculable
    /**
     * Retorna el valor final del tiquete (tarifaBase * (1 - descuento)).
     */
    @Override
    public double calcularTotal() {
        return valorFinal;
    }

    // Imprimible
    @Override
    public void imprimirDetalle() {
        System.out.println("=========== TICKET ===========");
        System.out.println("Pasajero    : " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")");
        System.out.println("Vehiculo    : " + vehiculo.getClass().getSimpleName() + " - " + vehiculo.getPlaca());
        System.out.println("Fecha       : " + fechaCompra);
        System.out.println("Origen      : " + origen);
        System.out.println("Destino     : " + destino);
        System.out.println("Tarifa base : $" + vehiculo.getTarifaBase());
        System.out.println("Descuento   : " + (int)(pasajero.calcularDescuento() * 100) + "%");
        System.out.println("Valor final : $" + valorFinal);
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return pasajero.getCedula() + ";" +
                vehiculo.getPlaca() + ";" +
                fechaCompra + ";" +
                origen + ";" +
                destino + ";" +
                valorFinal;
    }
}