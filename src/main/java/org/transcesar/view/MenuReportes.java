package org.transcesar.view;
import  java.util.Scanner;
import org.transcesar.Logica.TicketService;

public class MenuReportes {

    private Scanner sc = new Scanner(System.in);
    private TicketService ticketService;

    public MenuReportes(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    private void menuTipoVehiculo() {
        UI.dibujarLinea('─');
        System.out.println("  Seleccione tipo: 1. Buseta, 2. MicroBus, 3. Bus");
        System.out.print("  Opcion: ");
        int sel = Integer.parseInt(sc.nextLine());
        String tipo = (sel == 1) ? "Buseta" : (sel == 2) ? "MicroBus" : "Bus";
        ticketService.listarPorTipoVehiculo(tipo);
    }
    
    private void menuTipoPasajero() {
        UI.dibujarLinea('─');
        System.out.println("  Seleccione tipo: 1. Regular, 2. Estudiante, 3. AdultoMayor");
        System.out.print("  Opcion: ");
        int sel = Integer.parseInt(sc.nextLine());
        String tipo = (sel == 1) ? "PasajeroRegular" : (sel == 2) ? "PasajeroEstudiante" : "PasajeroAdultoMayor";
        ticketService.listarPorTipoPasajero(tipo);
    }

    public void  mostrar(){
        int opcion;

        do {
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║        [Q] MODULO DE REPORTES Y FILTROS            ║");
            System.out.println("╠════════════════════════════════════════════════════╣");
            System.out.println("║  1. Total Recaudado (Historico)                    ║");
            System.out.println("║  2. Pasajeros por tipo                             ║");
            System.out.println("║  3. Vehiculo con mas tickets vendidos              ║");
            System.out.println("║  4. Tickets por fecha (Filtro)                    ║");
            System.out.println("║  5. Tickets por tipo de vehiculo                  ║");
            System.out.println("║  6. Tickets por tipo de pasajero                  ║");
            System.out.println("║  7. Resumen del dia actual                         ║");
            System.out.println("║  0. Volver al Menu Principal                       ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.print("  Seleccione una opcion: ");

            opcion = Integer.parseInt(sc.nextLine());
            
            switch (opcion){
                case 1:
                    UI.dibujarLinea('─');
                    System.out.println("  TOTAL RECAUDADO (HISTORICO)");
                    UI.dibujarLinea('─');
                    System.out.println("  Total: $" + ticketService.calcularTotalRecaudado());
                    UI.dibujarLinea('─');
                    break;
                case 2:
                    UI.dibujarLinea('─');
                    System.out.println("  PASAJEROS POR TIPO");
                    UI.dibujarLinea('─');
                    ticketService.contarPasajerosPorTipo();
                    UI.dibujarLinea('─');
                    break;
                case 3:
                    UI.dibujarLinea('─');
                    System.out.println("  VEHICULO CON MAS TICKETS");
                    UI.dibujarLinea('─');
                    ticketService.obtenerVehiculoConMasTickets();
                    UI.dibujarLinea('─');
                    break;
                case 4:
                    System.out.print("  Ingrese la fecha (YYYY-MM-DD): ");
                    String fecha = sc.nextLine();
                    UI.dibujarLinea('─');
                    System.out.println("  TICKETS DEL: " + fecha);
                    UI.dibujarLinea('─');
                    ticketService.listarTicketsPorFecha(fecha);
                    UI.dibujarLinea('─');
                    break;
                case 5:
                    menuTipoVehiculo();
                    break;
                case 6:
                    menuTipoPasajero();
                    break;
                case 7:
                    UI.dibujarLinea('═');
                    ticketService.resumenDiaActual();
                    UI.dibujarLinea('═');
                    break;
                case 0:
                    System.out.println("  Volviendo al menu principal...");
                    break;
                default:
                    UI.error("Opcion invalida");
            }

        }while ((opcion !=0));


            }

}
