package org.transcesar.view;
import org.transcesar.Logica.PersonaService;
import org.transcesar.Modelo.Conductor;
import org.transcesar.Modelo.PasajeroRegular;
import org.transcesar.Modelo.PasajeroEstudiante;

import java.util.Scanner;

public class MenuPersonas {


    private Scanner sc = new Scanner(System.in);
     private PersonaService personaService;

     public MenuPersonas(PersonaService personaService) {
         this.personaService = personaService;
     }

            public void  mostrar(){
                int opcion;
                do {
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════╗");
                    System.out.println("║           [P] MODULO DE GESTION DE PERSONAS       ║");
                    System.out.println("╠════════════════════════════════════════════════════╣");
                    System.out.println("║  1. Registrar Conductor                           ║");
                    System.out.println("║  2. Registrar Pasajero regular                    ║");
                    System.out.println("║  3. Registrar Pasajero Estudiante                 ║");
                    System.out.println("║  4. Registrar Pasajero Adulto mayor               ║");
                    System.out.println("║  5. Listar personas                              ║");
                    System.out.println("║  0. Volver al Menu Principal                     ║");
                    System.out.println("╚════════════════════════════════════════════════════╝");
                    System.out.print("  Seleccione una opcion: ");
                    
                    opcion = Integer.parseInt(sc.nextLine());
                    
                    switch (opcion) {
                        case 1:
                            System.out.print("  Cedula: ");
                            String cedulaCond = sc.nextLine();
                            System.out.print("  Nombre: ");
                            String nombreCond = sc.nextLine();
                            System.out.print("  Numero de licencia: ");
                            String licencia = sc.nextLine();
                            System.out.print("  Categoria licencia (B1/B2/C1/C2): ");
                            String categoria = sc.nextLine();
                            Conductor conductor = new Conductor(cedulaCond, nombreCond, licencia, categoria);
                            if (personaService.registrarConductor(conductor)) {
                                UI.exito("Conductor registrado exitosamente");
                            } else {
                                UI.error("No se pudo registrar el conductor");
                            }
                            break;
                        case 2:
                            System.out.print("  Cedula: ");
                            String cedulaReg = sc.nextLine();
                            System.out.print("  Nombre: ");
                            String nombreReg = sc.nextLine();
                            System.out.print("  Fecha nacimiento (YYYY-MM-DD): ");
                            String fechaReg = sc.nextLine();
                            PasajeroRegular pasajeroReg = new PasajeroRegular(cedulaReg, nombreReg, fechaReg);
                            if (personaService.registrarPasajero(pasajeroReg)) {
                                UI.exito("Pasajero regular registrado");
                            } else {
                                UI.error("No se pudo registrar el pasajero");
                            }
                            break;
                        case 3:
                            System.out.print("  Cedula: ");
                            String cedulaEst = sc.nextLine();
                            System.out.print("  Nombre: ");
                            String nombreEst = sc.nextLine();
                            System.out.print("  Fecha nacimiento (YYYY-MM-DD): ");
                            String fechaEst = sc.nextLine();
                            PasajeroEstudiante pasajeroEst = new PasajeroEstudiante(cedulaEst, nombreEst, fechaEst);
                            if (personaService.registrarPasajero(pasajeroEst)) {
                                UI.exito("Pasajero estudiante registrado");
                            } else {
                                UI.error("No se pudo registrar el pasajero");
                            }
                            break;
                        case 4:
                            System.out.print("  Cedula: ");
                            String cedulaAdulto = sc.nextLine();
                            System.out.print("  Nombre: ");
                            String nombreAdulto = sc.nextLine();
                            System.out.print("  Fecha nacimiento (YYYY-MM-DD): ");
                            String fechaAdulto = sc.nextLine();
                            PasajeroRegular adulto = new PasajeroRegular(cedulaAdulto, nombreAdulto, fechaAdulto);
                            if (personaService.registrarPasajero(adulto)) {
                                UI.exito("Pasajero adulto mayor registrado");
                            } else {
                                UI.error("No se pudo registrar el pasajero");
                            }
                            break;
                        case 5:
                            UI.dibujarLinea('─');
                            System.out.println("                    CONDUCTORES");
                            UI.dibujarLinea('─');
                            personaService.listarConductores();
                            UI.dibujarLinea('─');
                            System.out.println("                    PASAJEROS");
                            UI.dibujarLinea('─');
                            personaService.listarPasajeros();
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
