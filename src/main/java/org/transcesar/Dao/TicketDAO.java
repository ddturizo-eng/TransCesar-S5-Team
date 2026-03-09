package org.transcesar.Dao;

import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.Ticket;
import org.transcesar.Modelo.Vehiculo;

import java.io.*;
import java.util.ArrayList;

/**
 * TicketDAO
 * Responsable de leer y escribir tickets.txt
 * Formato: cedula_pasajero;placa_vehiculo;fechaCompra;origen;destino;valorFinal
 */
public class TicketDAO {

    private static final String ARCHIVO_TICKETS = "tickets.txt";

    public void guardarTicket(Ticket t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_TICKETS, true))) {
            bw.write(t.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar ticket: " + e.getMessage());
        }
    }

    /**
     * Carga los tickets desde tickets.txt enlazando con las listas ya en memoria.
     * @param pasajeros lista cargada desde PersonaDAO
     * @param vehiculos lista cargada desde VehiculoDAO
     */
    public ArrayList<Ticket> cargarTickets(ArrayList<Pasajero> pasajeros, ArrayList<Vehiculo> vehiculos) {
        ArrayList<Ticket> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_TICKETS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(";");
                // cedula;placa;fecha;origen;destino;valorFinal
                if (campos.length == 6) {
                    Pasajero pasajero = buscarPasajero(pasajeros, campos[0]);
                    Vehiculo vehiculo = buscarVehiculo(vehiculos, campos[1]);

                    if (pasajero != null && vehiculo != null) {
                        lista.add(new Ticket(pasajero, vehiculo, campos[2], campos[3], campos[4]));
                    } else {
                        System.out.println("Advertencia: no se pudo reconstruir ticket → " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tickets: " + e.getMessage());
        }
        return lista;
    }

    // Helpers

    private Pasajero buscarPasajero(ArrayList<Pasajero> lista, String cedula) {
        for (Pasajero p : lista) {
            if (p.getCedula().equals(cedula)) return p;
        }
        return null;
    }

    private Vehiculo buscarVehiculo(ArrayList<Vehiculo> lista, String placa) {
        for (Vehiculo v : lista) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }
}