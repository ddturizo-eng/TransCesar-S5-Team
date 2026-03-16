package org.transcesar.view;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

        MenuVehiculos menuVeh = new MenuVehiculos();
        MenuPersonas menuPer = new MenuPersonas();
        MenuTickets menuTick = new MenuTickets();
        MenuReportes menuRep = new MenuReportes();
       int op;
       do {
        System.out.println("Menu Principal Trancesar S.A.S");
        System.out.println("1. Gestion de Vehiculos");
        System.out.println("2. Gestion de Personas");
        System.out.println("3. Gestion de Tickets");
        System.out.println("4. Gestion de Reportes");
        System.out.println("0. Salir");
        op = sc.nextInt();

        switch (op) {
            case 1:
                menuVeh.mostrar();
                break;
            case 2:
                menuPer.mostrar();
                break;
            case 3:
                menuTick.mostrar();
                break;
            case 4:
                menuRep.mostrar();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
        }
         } while (op != 0);
        System.out.println("Hasta pronto.");
    }
}