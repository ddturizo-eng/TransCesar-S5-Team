package org.transcesar.Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Clase Reserva
 * Representa el apartado temporal de un cupo en un vehículo por parte de un pasajero.
 * Estados posibles: "ACTIVA", "CONVERTIDA", "CANCELADA"
 */
public class Reserva implements Imprimible {

    public static final String ACTIVA     = "ACTIVA";
    public static final String CONVERTIDA = "CONVERTIDA";
    public static final String CANCELADA  = "CANCELADA";

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String   codigoReserva;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private String   fechaCreacion;  // formato yyyy-MM-dd HH:mm:ss
    private String   fechaViaje;     // formato yyyy-MM-dd
    private String   estado;         // ACTIVA | CONVERTIDA | CANCELADA

    public Reserva(String codigoReserva, Pasajero pasajero, Vehiculo vehiculo,
                   String fechaCreacion, String fechaViaje) {
        this.codigoReserva = codigoReserva;
        this.pasajero      = pasajero;
        this.vehiculo      = vehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje    = fechaViaje;
        this.estado        = ACTIVA; // siempre nace ACTIVA
    }

    // Getters y Setters

    public String   getCodigoReserva()               { return codigoReserva; }
    public void     setCodigoReserva(String c)       { this.codigoReserva = c; }

    public Pasajero getPasajero()                    { return pasajero; }
    public void     setPasajero(Pasajero p)          { this.pasajero = p; }

    public Vehiculo getVehiculo()                    { return vehiculo; }
    public void     setVehiculo(Vehiculo v)          { this.vehiculo = v; }

    public String   getFechaCreacion()               { return fechaCreacion; }
    public void     setFechaCreacion(String f)       { this.fechaCreacion = f; }

    public String   getFechaViaje()                  { return fechaViaje; }
    public void     setFechaViaje(String f)          { this.fechaViaje = f; }

    public String   getEstado()                      { return estado; }
    public void     setEstado(String estado)         { this.estado = estado; }

    // Métodos de negocio

    /**
     * Retorna true si han pasado más de 24 horas desde fechaCreacion.
     */
    public boolean estaVencida() {
        if (!estado.equals(ACTIVA)) return false;
        try {
            LocalDateTime creacion = LocalDateTime.parse(fechaCreacion, FORMATO);
            long horas = ChronoUnit.HOURS.between(creacion, LocalDateTime.now());
            return horas > 24;
        } catch (Exception e) {
            System.out.println("Advertencia: no se pudo calcular vencimiento de reserva " + codigoReserva);
            return false;
        }
    }

    // Imprimible

    @Override
    public void imprimirDetalle() {
        System.out.println("========== RESERVA ==========");
        System.out.println("Codigo       : " + codigoReserva);
        System.out.println("Pasajero     : " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")");
        System.out.println("Vehículo     : " + vehiculo.getClass().getSimpleName() + " - " + vehiculo.getPlaca());
        System.out.println("Fecha creac. : " + fechaCreacion);
        System.out.println("Fecha viaje  : " + fechaViaje);
        System.out.println("Estado       : " + estado);
        System.out.println("Vencida      : " + (estaVencida() ? "Sí" : "No"));
        System.out.println("=============================");
    }

    // ── toString para reservas.txt ────────────────────────────
    // Formato: codigo;cedulaPasajero;placaVehiculo;fechaCreacion;fechaViaje;estado

    @Override
    public String toString() {
        return codigoReserva        + ";" +
                pasajero.getCedula() + ";" +
                vehiculo.getPlaca()  + ";" +
                fechaCreacion        + ";" +
                fechaViaje           + ";" +
                estado;
    }
}