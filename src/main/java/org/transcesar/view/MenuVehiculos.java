package org.transcesar.view;

import org.transcesar.Logica.VehiculoService;
import org.transcesar.Modelo.Buseta;
import org.transcesar.Modelo.MicroBus;
import org.transcesar.Modelo.Bus;
import org.transcesar.Modelo.Vehiculo;
import java.util.Scanner;
public class MenuVehiculos {

  private Scanner sc = new Scanner(System.in);

    private VehiculoService vehiculoService;

    public MenuVehiculos(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

            public void  mostrar(){
                int opcion;
                do {
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════╗");
                    System.out.println("║           [V] MODULO DE GESTION DE VEHICULOS       ║");
                    System.out.println("╠════════════════════════════════════════════════════╣");
                    System.out.println("║  1. Registrar Buseta                               ║");
                    System.out.println("║  2. Registrar MicroBus                             ║");
                    System.out.println("║  3. Registrar Bus                                  ║");
                    System.out.println("║  4. Registrar Vehiculo Personalizado              ║");
                    System.out.println("║  5. Listar vehiculos                              ║");
                    System.out.println("║  0. Volver al Menu Principal                     ║");
                    System.out.println("╚════════════════════════════════════════════════════╝");
                    System.out.print("  Seleccione una opcion: ");
                    
                    opcion = Integer.parseInt(sc.nextLine());
                    
                    switch (opcion) {
                        case 1:
                            System.out.print("  Placa: ");
                            String placa1 = sc.nextLine();
                            System.out.print("  Tarifa base: ");
                            double tarifa1 = Double.parseDouble(sc.nextLine());
                            System.out.print("  Capacidad maxima: ");
                            int capacidad1 = Integer.parseInt(sc.nextLine());
                            Vehiculo buseta = new Buseta(placa1, null);
                            buseta.setTarifaBase(tarifa1);
                            buseta.setCapacidadMaxima(capacidad1);
                            if (vehiculoService.registrarVehiculo(buseta)) {
                                UI.exito("Buseta registrada con placa " + placa1);
                            }
                            break;
                        case 2:
                            System.out.print("  Placa: ");
                            String placa2 = sc.nextLine();
                            System.out.print("  Tarifa base: ");
                            double tarifa2 = Double.parseDouble(sc.nextLine());
                            System.out.print("  Capacidad maxima: ");
                            int capacidad2 = Integer.parseInt(sc.nextLine());
                            Vehiculo microbus = new MicroBus(placa2, null);
                            microbus.setTarifaBase(tarifa2);
                            microbus.setCapacidadMaxima(capacidad2);
                            if (vehiculoService.registrarVehiculo(microbus)) {
                                UI.exito("MicroBus registrado con placa " + placa2);
                            }
                            break;
                        case 3:
                            System.out.print("  Placa: ");
                            String placa3 = sc.nextLine();
                            System.out.print("  Tarifa base: ");
                            double tarifa3 = Double.parseDouble(sc.nextLine());
                            System.out.print("  Capacidad maxima: ");
                            int capacidad3 = Integer.parseInt(sc.nextLine());
                            Vehiculo bus = new Bus(placa3, null);
                            bus.setTarifaBase(tarifa3);
                            bus.setCapacidadMaxima(capacidad3);
                            if (vehiculoService.registrarVehiculo(bus)) {
                                UI.exito("Bus registrado con placa " + placa3);
                            }
                            break;
                        case 4:
                            System.out.print("  Placa: ");
                            String placa4 = sc.nextLine();
                            System.out.print("  Tarifa base: ");
                            double tarifa4 = Double.parseDouble(sc.nextLine());
                            System.out.print("  Capacidad maxima: ");
                            int capacidad4 = Integer.parseInt(sc.nextLine());
                            Vehiculo vehiculo4 = new Buseta(placa4, null);
                            vehiculo4.setTarifaBase(tarifa4);
                            vehiculo4.setCapacidadMaxima(capacidad4);
                            if (vehiculoService.registrarVehiculo(vehiculo4)) {
                                UI.exito("Vehiculo personalizado registrado con placa " + placa4);
                            }
                            break;
                        case 5:
                            UI.dibujarLinea('─');
                            System.out.println("              LISTADO DE VEHICULOS");
                            UI.dibujarLinea('─');
                            for (Vehiculo v : vehiculoService.listarVehiculos()) {
                                System.out.println("  [" + v.getClass().getSimpleName() + "] Placa: " + v.getPlaca());
                            }
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
