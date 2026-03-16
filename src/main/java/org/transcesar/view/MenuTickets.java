package org.transcesar.view;
import java.util.Scanner;
import org.transcesar.Logica.*;
import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.Vehiculo;

public class MenuTickets {

    private  Scanner sc = new Scanner(System.in);
    private TicketService ticketService;
    private PersonaService personaService;
    private VehiculoService vehiculoService;

    public MenuTickets(TicketService ticketService, PersonaService personaService, VehiculoService vehiculoService) {
        this.ticketService = ticketService;
        this.personaService = personaService;
        this.vehiculoService = vehiculoService;
    }
            public void  mostrar(){
                int opcion;
                do {
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════╗");
                    System.out.println("║           [$] MODULO DE GESTION DE TICKETS        ║");
                    System.out.println("╠════════════════════════════════════════════════════╣");
                    System.out.println("║  1. Vender ticket                                  ║");
                    System.out.println("║  2. Listar tickets                                 ║");
                    System.out.println("║  0. Volver al Menu Principal                      ║");
                    System.out.println("╚════════════════════════════════════════════════════╝");
                    System.out.print("  Seleccione una opcion: ");
                    
                    opcion = Integer.parseInt(sc.nextLine());
                    
                    switch (opcion) {
                        case 1:
                            System.out.print("  Cedula del pasajero: ");
                            String cedula = sc.nextLine();
                            Pasajero pasajero = personaService.buscarPasajeroPorCedula(cedula);
                            if (pasajero == null) {
                                UI.error("No se encontro un pasajero con esa cedula");
                                break;
                            }
                            
                            System.out.print("  Placa del vehiculo: ");
                            String placa = sc.nextLine();
                            Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
                            if (vehiculo == null) {
                                UI.error("No se encontro un vehiculo con esa placa");
                                break;
                            }
                            
                            System.out.print("  Origen: ");
                            String origen = sc.nextLine();
                            System.out.print("  Destino: ");
                            String destino = sc.nextLine();
                            
                            if (ticketService.venderTicket(pasajero, vehiculo, origen, destino) != null) {
                                UI.exito("Ticket vendido exitosamente");
                            }
                            break;
                        case 2:
                            UI.dibujarLinea('─');
                            System.out.println("              LISTADO DE TICKETS");
                            UI.dibujarLinea('─');
                            ticketService.listarTickets();
                            UI.dibujarLinea('─');
                            break;
                        case 0:
                            System.out.println("  Volviendo al menu principal...");
                            break;
                        default:
                            UI.error("Opcion invalida");
                    }
                } while (opcion != 0);
            }
}
