package org.transcesar.Logica;

import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.Reserva;
import org.transcesar.Modelo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class ReservaService {

    private List<Reserva> reservas = new ArrayList<>();
    private int contador = 1;

    public Reserva crearReserva(Pasajero pasajero, Vehiculo vehiculo, String fechaViaje) {
        String codigoReserva = "RES-" + contador++;
        String fechaCreacion = new java.util.Date().toString();
        Reserva nueva = new Reserva(codigoReserva, pasajero, vehiculo, fechaCreacion, fechaViaje);
        reservas.add(nueva);
        return nueva;
    }

    public boolean cancelarReserva(String codigo) {
        for (Reserva r : reservas) {
            if (r.getCodigoReserva().equals(codigo)) {
                r.setEstado(Reserva.CANCELADA);
                return true;
            }
        }
        return false;
    }

    public List<Reserva> listarReservasActivas() {
        List<Reserva> activas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getEstado().equals(Reserva.ACTIVA)) {
                activas.add(r);
            }
        }
        return activas;
    }

    public List<Reserva> listarHistorialPasajero(String cedula) {
        List<Reserva> historial = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getPasajero().getCedula().equals(cedula)) {
                historial.add(r);
            }
        }
        return historial;
    }
}
