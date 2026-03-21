package org.transcesar.view;

import java.util.Scanner;
import org.transcesar.Dao.*;
import org.transcesar.Logica.*;

public class Main {

    private static void mostrarBanner() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                      ║");
        System.out.println("║     ██████╗ ███████╗████████╗██████╗  ██████╗      ║");
        System.out.println("║     ██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██╔═══██╗     ║");
        System.out.println("║     ██████╔╝█████╗     ██║   ██████╔╝██║   ██║     ║");
        System.out.println("║     ██╔══██╗██╔══╝     ██║   ██╔══██╗██║   ██║     ║");
        System.out.println("║     ██║  ██║███████╗   ██║   ██║  ██║╚██████╔╝     ║");
        System.out.println("║     ╚═╝  ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝      ║");
        System.out.println("║                                                      ║");
        System.out.println("║                      S 5                             ║");
        System.out.println("║            S I S T E M A   D E   T R A N S P O R T E ║");
        System.out.println("║                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PersonaDAO personaDAO   = new PersonaDAO();
        TicketDAO ticketDAO     = new TicketDAO();
        RutaDAO rutaDAO         = new RutaDAO();
        VehiculoDao vehiculoDAO = new VehiculoDao(rutaDAO);
        ReservaDAO reservaDAO   = new ReservaDAO();

        //ORDEN DE INSTANCIACION EN MAIN  RutaService -> VehiculoService(rutaService) -> PersonaService -> FestivoService
        //        // -> TicketService(vehiculoService, festivoService) -> ReservaService(ticketService)

        RutaService rutaService         = new RutaService(rutaDAO);
        VehiculoService vehiculoService = new VehiculoService(vehiculoDAO, rutaService);
        PersonaService personaService   = new PersonaService(personaDAO);
        FestivoService festivoService   = new FestivoService();
        TicketService ticketService     = new TicketService(
                ticketDAO,
                vehiculoService,
                festivoService,
                personaService.getPasajeros(),
                vehiculoService.getVehiculos()
        );
        ReservaService reservaService   = new ReservaService(ticketService);

        // ── VERIFICAR RESERVAS VENCIDAS AL INICIAR ────────────────
        int vencidas = reservaService.verificarVencidas();
        if (vencidas > 0) {
            System.out.println("  [!] Se cancelaron " + vencidas + " reservas vencidas al iniciar.");
        }

        // ── MENÚS ─────────────────────────────────────────────────
        MenuVehiculos menuVeh  = new MenuVehiculos(vehiculoService);
        MenuPersonas  menuPer  = new MenuPersonas(personaService);
        MenuTickets   menuTick = new MenuTickets(ticketService, personaService, vehiculoService);
        MenuReportes  menuRep  = new MenuReportes(ticketService);
        MenuReservas  menuRes  = new MenuReservas(reservaService, personaService, vehiculoService);

        int op;
        mostrarBanner();
        do {
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║            MENU PRINCIPAL - TRANS CESAR            ║");
            System.out.println("╠════════════════════════════════════════════════════╣");
            System.out.println("║  [V] 1. Gestion de Vehiculos                      ║");
            System.out.println("║  [P] 2. Gestion de Personas                       ║");
            System.out.println("║  [$] 3. Gestion de Tickets                        ║");
            System.out.println("║  [Q] 4. Gestion de Reportes                       ║");
            System.out.println("║  [R] 5. Gestion de Reservas                       ║");
            System.out.println("║       0. Salir                                     ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.print("  Seleccione una opcion: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: menuVeh.mostrar();  break;
                case 2: menuPer.mostrar();  break;
                case 3: menuTick.mostrar(); break;
                case 4: menuRep.mostrar();  break;
                case 5: menuRes.mostrar();  break;
                case 0:
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════╗");
                    System.out.println("║     GRACIAS POR USAR TRANS CESAR. HASTA PRONTO!   ║");
                    System.out.println("╚════════════════════════════════════════════════════╝");
                    break;
                default:
                    UI.error("Opcion no valida, intente nuevamente.");
            }
        } while (op != 0);
    }
}