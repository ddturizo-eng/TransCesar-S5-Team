package org.transcesar.view;
import java.util.Scanner;

public class MenuPersonas {


    private Scanner sc = new Scanner(System.in);

            public void  mostrar(){

                System.out.println(" Gestion de personas");
                System.out.println("1. Registrar Conductor");
                System.out.println("2. Registrar Pasajero regular");
                System.out.println("3. Registrar Pasajero Estudiante");
                System.out.println("4. Registrar Pasajero Adulto mayor");
                System.out.println("5. Listar personas");
                System.out.println("0. Volver");

            }
}
