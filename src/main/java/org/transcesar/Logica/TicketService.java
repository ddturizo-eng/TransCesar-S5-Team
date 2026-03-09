package org.transcesar.Logica;

import org.transcesar.Dao.TicketDAO;
import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.PasajeroAdultoMayor;
import org.transcesar.Modelo.PasajeroEstudiante;
import org.transcesar.Modelo.PasajeroRegular;
import org.transcesar.Modelo.Ticket;
import org.transcesar.Modelo.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * TicketService
 * Aplica las reglas de negocio para la venta de tickets y estadísticas.
 */
public class TicketService {

    private TicketDAO ticketDAO;
    private VehiculoService vehiculoService;
    private ArrayList<Ticket> tickets;

    public TicketService(TicketDAO ticketDAO,
                         VehiculoService vehiculoService,
                         ArrayList<Pasajero> pasajerosCargados,
                         ArrayList<Vehiculo> vehiculosCargados) {
        this.ticketDAO       = ticketDAO;
        this.vehiculoService = vehiculoService;
        this.tickets         = ticketDAO.cargarTickets(pasajerosCargados, vehiculosCargados);
    }

    /**
     * Vende un ticket verificando que el vehículo tenga cupos disponibles.
     * Incrementa pasajerosActuales del vehículo si la venta es exitosa.
     * @return el Ticket generado, o null si no hay cupos.
     */
    public Ticket venderTicket(Pasajero pasajero, Vehiculo vehiculo, String origen, String destino) {
        if (!vehiculoService.tieneCuposDisponibles(vehiculo)) {
            System.out.println("ERROR: El vehículo " + vehiculo.getPlaca() + " no tiene cupos disponibles.");
            return null;
        }
        String fecha  = LocalDate.now().toString();
        Ticket ticket = new Ticket(pasajero, vehiculo, fecha, origen, destino);

        // Actualizar contador de pasajeros en el vehículo
        vehiculo.setPasajerosActuales(vehiculo.getPasajerosActuales() + 1);

        tickets.add(ticket);
        ticketDAO.guardarTicket(ticket);
        System.out.println("Ticket vendido exitosamente. Valor final: $" + ticket.getValorFinal());
        return ticket;
    }

    // Estadísticas

    /**
     * Calcula el total de dinero recaudado en todas las ventas.
     */
    public double calcularTotalRecaudado() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.calcularTotal();
        }
        return total;
    }

    /**
     * Muestra cuántos pasajeros de cada tipo han comprado tickets.
     */
    public void contarPasajerosPorTipo() {
        int regulares    = 0;
        int estudiantes  = 0;
        int adultosMayor = 0;

        for (Ticket t : tickets) {
            Pasajero p = t.getPasajero();
            if (p instanceof PasajeroRegular)          regulares++;
            else if (p instanceof PasajeroEstudiante)  estudiantes++;
            else if (p instanceof PasajeroAdultoMayor) adultosMayor++;
        }

        System.out.println("===== PASAJEROS POR TIPO =====");
        System.out.println("Regular      : " + regulares);
        System.out.println("Estudiante   : " + estudiantes);
        System.out.println("Adulto Mayor : " + adultosMayor);
        System.out.println("Total tickets: " + tickets.size());
        System.out.println("==============================");
    }

    /**
     * Identifica el vehículo con más tickets vendidos.
     * @return el Vehiculo con más tickets, o null si no hay ninguno.
     */
    public Vehiculo obtenerVehiculoConMasTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
            return null;
        }

        HashMap<String, Integer> conteo        = new HashMap<>();
        HashMap<String, Vehiculo> mapaVehiculos = new HashMap<>();

        for (Ticket t : tickets) {
            String placa = t.getVehiculo().getPlaca();
            conteo.put(placa, conteo.getOrDefault(placa, 0) + 1);
            mapaVehiculos.put(placa, t.getVehiculo());
        }

        String placaMax = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() > max) {
                max      = entry.getValue();
                placaMax = entry.getKey();
            }
        }

        Vehiculo ganador = mapaVehiculos.get(placaMax);
        System.out.println("Vehículo con más tickets: " + ganador.getPlaca() +
                " (" + ganador.getClass().getSimpleName() + ") → " + max + " ticket(s)");
        return ganador;
    }

    public void listarTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
            return;
        }
        tickets.forEach(Ticket::imprimirDetalle);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}