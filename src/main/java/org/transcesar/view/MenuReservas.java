package org.transcesar.view;

import org.transcesar.Logica.ReservaService;
import org.transcesar.Logica.PersonaService;
import org.transcesar.Logica.VehiculoService;
import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.Reserva;
import org.transcesar.Modelo.Vehiculo;
import java.util.List;
import java.util.Scanner;



public class MenuReservas {
    private ReservaService reservaService;
    private PersonaService personaService;
    private VehiculoService vehiculoService;
    private Scanner sc = new Scanner(System.in);

    public MenuReservas(ReservaService reservaService, PersonaService personaService, VehiculoService vehiculoService) {
        this.reservaService = reservaService;
        this.personaService = personaService;
        this.vehiculoService = vehiculoService;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║         [R] MODULO DE GESTION DE RESERVAS          ║");
            System.out.println("╠════════════════════════════════════════════════════╣");
            System.out.println("║  1. Crear reserva                                  ║");
            System.out.println("║  2. Cancelar reserva                               ║");
            System.out.println("║  3. Listar reservas activas                        ║");
            System.out.println("║  4. Historial de pasajero                          ║");
            System.out.println("║  5. Convertir reserva en ticket                    ║");
            System.out.println("║  6. Verificar reservas vencidas                    ║");
            System.out.println("║  0. Volver al Menu Principal                       ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.print("  Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {


                case 1: crearReserva();
                break;
                case 2: cancelarReserva();
                break;
                case 3: listarActivas();
                break;
                case 4: historialPasajero();
                break;
                case 5: convertirReserva();
                break;
                case 6: verificarVencidas();
                break;
                case 0: System.out.println("  Volviendo al menu principal...");
                break;
                default: System.out.println("  Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
    private void crearReserva() {
        System.out.print("Cédula del pasajero: ");
        String cedula = sc.nextLine();
        System.out.print("Placa del vehículo: ");
        String placa = sc.nextLine();
        System.out.print("Fecha del viaje (YYYY-MM-DD): ");
        String fechaViaje = sc.nextLine();

        Pasajero pasajero = personaService.buscarPasajeroPorCedula(cedula);
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);

        if (pasajero == null) {
            System.out.println("Pasajero no encontrado.");
            return;
        }
        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return;
        }

        boolean resultado = reservaService.crearReserva(pasajero, vehiculo, fechaViaje);
        if (resultado) {
            System.out.println("Reserva creada exitosamente.");
        } else {
            System.out.println("No se pudo crear la reserva. Verifique cupos o reserva duplicada.");
        }
    }

    private void cancelarReserva() {
        System.out.print("Código de la reserva: ");
        String codigo = sc.nextLine();
        boolean resultado = reservaService.cancelarReserva(codigo);
        if (resultado) {
            System.out.println("Reserva cancelada exitosamente.");
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }

    private void listarActivas() {
        List<Reserva> activas = reservaService.listarReservasActivas();
        if (activas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            return;
        }
        System.out.println("\n=== RESERVAS ACTIVAS ===");
        for (Reserva r : activas) {
            r.imprimirDetalle();
        }
    }

    private void historialPasajero() {
        System.out.print("Cédula del pasajero: ");
        String cedula = sc.nextLine();
        List<Reserva> historial = reservaService.listarHistorialPasajero(cedula);
        if (historial.isEmpty()) {
            System.out.println("No se encontraron reservas para ese pasajero.");
            return;
        }
        System.out.println("\n=== HISTORIAL DE RESERVAS ===");
        for (Reserva r : historial) {
            r.imprimirDetalle();
        }
    }

    private void convertirReserva() {
        System.out.print("Código de la reserva a convertir: ");
        String codigo = sc.nextLine();
        boolean resultado = reservaService.convertirReserva(codigo);
        if (resultado) {
            System.out.println("Reserva convertida en ticket exitosamente.");
        } else {
            System.out.println("No se pudo convertir la reserva. Verifique el código.");
        }
    }

    private void verificarVencidas() {
        int canceladas = reservaService.verificarVencidas();
        System.out.println("Verificación completada. Reservas canceladas automáticamente: " + canceladas);
    }
}


