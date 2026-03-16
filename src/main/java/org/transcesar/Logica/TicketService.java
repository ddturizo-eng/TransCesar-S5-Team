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

    private static final int LIMITE_TICKETS_POR_DIA = 3;

    private TicketDAO ticketDAO;
    private VehiculoService vehiculoService;
    private FestivoService festivoService;      // <-- NUEVO
    private ArrayList<Ticket> tickets;

    public TicketService(TicketDAO ticketDAO,
                         VehiculoService vehiculoService,
                         FestivoService festivoService,             // <-- NUEVO
                         ArrayList<Pasajero> pasajerosCargados,
                         ArrayList<Vehiculo> vehiculosCargados) {
        this.ticketDAO       = ticketDAO;
        this.vehiculoService = vehiculoService;
        this.festivoService  = festivoService;                      // <-- NUEVO
        this.tickets         = ticketDAO.cargarTickets(pasajerosCargados, vehiculosCargados);
    }

    /**
     * Cuenta cuántos tickets tiene un pasajero (por cédula) en una fecha específica.
     * @param cedula Cédula del pasajero
     * @param fecha  Fecha en formato YYYY-MM-DD
     * @return número de tickets del pasajero en esa fecha
     */
    public int contarTicketsPasajeroPorFecha(String cedula, String fecha) {
        int contador = 0;
        for (Ticket t : tickets) {
            if (t.getPasajero().getCedula().equals(cedula) &&
                    t.getFechaCompra().equals(fecha)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Vende un ticket verificando:
     * 1. Que el pasajero no haya alcanzado el límite de 3 tickets por día.
     * 2. Que el vehículo tenga cupos disponibles.
     * 3. Si la fecha es festivo, incrementa tarifaBase un 20% antes del descuento.
     * Orden de cálculo: tarifaBase × 1.20 (si festivo) × (1 - descuento)
     * @return el Ticket generado, o null si no se puede realizar la venta.
     */
    public Ticket venderTicket(Pasajero pasajero, Vehiculo vehiculo, String origen, String destino) {

        String fecha = LocalDate.now().toString();

        // Validación 1: límite de 3 tickets por pasajero por día
        int ticketsHoy = contarTicketsPasajeroPorFecha(pasajero.getCedula(), fecha);
        if (ticketsHoy >= LIMITE_TICKETS_POR_DIA) {
            System.out.println("El pasajero " + pasajero.getNombre() +
                    " ya tiene " + ticketsHoy + " tickets para hoy. No se puede realizar la venta.");
            return null;
        }

        // Validación 2: cupos disponibles en el vehículo
        if (!vehiculoService.tieneCuposDisponibles(vehiculo)) {
            System.out.println("ERROR: El vehículo " + vehiculo.getPlaca() + " no tiene cupos disponibles.");
            return null;
        }

        // Cálculo del valor — ORDEN: primero incremento festivo, luego descuento
        double tarifa = vehiculo.getTarifaBase();
        if (festivoService.esFestivo(fecha)) {
            tarifa = tarifa * 1.20;
            System.out.println("Día festivo: tarifa incrementada un 20% → $" + tarifa);
        }
        double valorFinal = tarifa * (1 - pasajero.calcularDescuento());

        Ticket ticket = new Ticket(pasajero, vehiculo, fecha, origen, destino, valorFinal);

        // Actualizar contador de pasajeros en el vehículo
        vehiculo.setPasajerosActuales(vehiculo.getPasajerosActuales() + 1);

        tickets.add(ticket);
        ticketDAO.guardarTicket(ticket);
        System.out.println("Ticket vendido exitosamente. Valor final: $" + ticket.getValorFinal());
        return ticket;
    }

    // ─── Estadísticas ────────────────────────────────────────────────────────

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

        HashMap<String, Integer> conteo         = new HashMap<>();
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