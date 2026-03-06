package org.transcesar.view;
// import org.transcesar.VehiculoService;

import java.util.Scanner;
public class MenuVehiculos {

  private Scanner sc = new Scanner(System.in);

            public void  mostrar(){

                System.out.println(" Gestion de vehiculos");
                System.out.println("1. Registrar Buseta");
                System.out.println("2. Registrar Camion");
                System.out.println("3. Registrar MicroBus");
                System.out.println("4. Registrar Bus");
                System.out.println("5. Listar vehículos");
                System.out.println("0. Volver");

            }

}
