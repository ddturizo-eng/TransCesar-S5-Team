package org.transcesar.Logica;

import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.Reserva;
import org.transcesar.Modelo.Ticket;
import org.transcesar.Modelo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class ReservaService {

    private List<Reserva> reservas = new ArrayList<>();
    private int contador = 1;
    private TicketService ticketService;

    public ReservaService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public boolean crearReserva(Pasajero pasajero, Vehiculo vehiculo, String fechaViaje) {

        // Regla 1: tickets vendidos + reservas activas <= capacidadMaxima

        int tickets = contarTicketsVehiculo(vehiculo.getPlaca());
        int reservasActivas = contarReservasActivasVehiculo(vehiculo.getPlaca());
        if (tickets + reservasActivas >= vehiculo.getCapacidadMaxima()) return false;

        // Regla 2: pasajero no puede tener más de una reserva activa para el mismo vehículo en la misma fecha

        for (Reserva r : reservas) {
            if (r.getEstado().equals("ACTIVA")
                    && r.getPasajero().getCedula().equals(pasajero.getCedula())
                    && r.getVehiculo().getPlaca().equals(vehiculo.getPlaca())
                    && r.getFechaViaje().equals(fechaViaje)) return false;
        }

        // Regla 3: cancelar reservas vencidas antes de crear
        
        for (Reserva r : reservas) {
            if (r.estaVencida()) {
                r.setEstado(Reserva.CANCELADA);
            }
        }

        String codigoReserva = "RES-" + contador++;
        String fechaCreacion = new java.util.Date().toString();
        Reserva nueva = new Reserva(codigoReserva, pasajero, vehiculo, fechaCreacion, fechaViaje);
        reservas.add(nueva);
        return true;
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

    public int verificarVencidas() {
        int canceladas = 0;
        for (Reserva r : reservas) {
            if (r.estaVencida()) {
                r.setEstado(Reserva.CANCELADA);
                canceladas++;
            }
        }
        return canceladas;
    }

    public boolean convertirReserva(String codigo) {
        for (Reserva r : reservas) {
            if (r.getCodigoReserva().equals(codigo)) {
                r.setEstado(Reserva.CONVERTIDA);
                ticketService.venderTicket(r.getPasajero(), r.getVehiculo(), "", r.getFechaViaje());
                return true;
            }
        }
        return false;
    }

    public int contarReservasActivasVehiculo(String placa) {
        int count = 0;
        for (Reserva r : reservas) {
            if (r.getEstado().equals(Reserva.ACTIVA)
                    && r.getVehiculo().getPlaca().equals(placa)) {
                count++;
            }
        }
        return count;
    }

    private int contarTicketsVehiculo(String placa) {
        int count = 0;
        for (Ticket t : ticketService.getTickets()) {
            if (t.getVehiculo().getPlaca().equals(placa)) {
                count++;
            }
        }
        return count;
    }
}