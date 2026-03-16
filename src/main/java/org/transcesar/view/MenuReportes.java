package org.transcesar.view;
import  java.util.Scanner;
import org.transcesar.Logica.TicketService;

public class MenuReportes {

    private Scanner sc = new Scanner(System.in);
    private TicketService ticketService;

    public MenuReportes(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    // Métodos auxiliares para los filtros específicos
    private void menuTipoVehiculo() {
        System.out.println("Seleccione tipo: 1. Buseta, 2. MicroBus, 3. Bus");
        int sel = sc.nextInt();
        String tipo = (sel == 1) ? "Buseta" : (sel == 2) ? "MicroBus" : "Bus";
        ticketService.listarPorTipoVehiculo(tipo);
    }
    private void menuTipoPasajero() {
        System.out.println("Seleccione tipo: 1. Regular, 2. Estudiante, 3. AdultoMayor");
        int sel = sc.nextInt();
        String tipo = (sel == 1) ? "PasajeroRegular" : (sel == 2) ? "PasajeroEstudiante" : "PasajeroAdultoMayor";
        ticketService.listarPorTipoPasajero(tipo);
    }

    public void  mostrar(){
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE REPORTES Y FILTROS =====");
            // Opciones del Taller 2 (se mantienen)
            System.out.println("1. Total Recaudado (Histórico)");
            System.out.println("2. Pasajeros por tipo");
            System.out.println("3. Vehículo con más tickets vendidos");

            // NUEVAS OPCIONES - Requerimiento 3 [cite: 99]
            System.out.println("4. Tickets por fecha (Filtro)");
            System.out.println("5. Tickets por tipo de vehículo");
            System.out.println("6. Tickets por tipo de pasajero");
            System.out.println("7. Resumen del día actual");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("Total recaudado: $" + ticketService.calcularTotalRecaudado());
                    break;
                    case 2:
                        ticketService.contarPasajerosPorTipo();
                        break;
                 case 3:
                     ticketService.obtenerVehiculoConMasTickets();
                     break;
                case 4:
                    System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
                    String fecha = sc.nextLine();
                    ticketService.listarTicketsPorFecha(fecha);
                    //System  .out.println("Total de tickets vendidos el " + fecha + ": " + ticketService.calcularTotalRecaudado());
                    break;
                case 5:
                    menuTipoVehiculo();
                    break;
                case 6:
                    menuTipoPasajero();
                    break;

                case 7:
                    ticketService.resumenDiaActual();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }

        }while ((opcion !=0));



            }

}
