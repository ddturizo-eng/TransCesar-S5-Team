package org.transcesar.Dao;

import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.Reserva;
import org.transcesar.Modelo.Vehiculo;

import java.io.*;
import java.util.ArrayList;

/**
 * ReservaDAO
 * Persiste y carga reservas desde reservas.txt
 * Formato: codigo;cedulaPasajero;placaVehiculo;fechaCreacion;fechaViaje;estado
 */
public class ReservaDAO {

    private static final String ARCHIVO_RESERVAS = "reservas.txt";

    // Guardar

    public void guardarReserva(Reserva r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_RESERVAS, true))) {
            bw.write(r.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar reserva: " + e.getMessage());
        }
    }

    /**
     * Actualiza el archivo completo reescribiéndolo con la lista actual.
     * Se usa cuando cambia el estado de una reserva (cancelar, convertir, vencer).
     */
    public void actualizarReservas(ArrayList<Reserva> reservas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_RESERVAS, false))) {
            for (Reserva r : reservas) {
                bw.write(r.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar reservas: " + e.getMessage());
        }
    }

    // Cargar

    /**
     * Carga las reservas desde reservas.txt reconstruyendo referencias a Pasajero y Vehiculo.
     * @param pasajeros lista cargada desde PersonaDAO
     * @param vehiculos lista cargada desde VehiculoDao
     */
    public ArrayList<Reserva> cargarReservas(ArrayList<Pasajero> pasajeros, ArrayList<Vehiculo> vehiculos) {
        ArrayList<Reserva> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_RESERVAS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                // codigo;cedulaPasajero;placaVehiculo;fechaCreacion;fechaViaje;estado
                String[] c = linea.split(";");
                if (c.length == 6) {
                    Pasajero pasajero = buscarPasajero(pasajeros, c[1]);
                    Vehiculo vehiculo = buscarVehiculo(vehiculos, c[2]);

                    if (pasajero == null || vehiculo == null) {
                        System.out.println("Advertencia: no se pudo reconstruir reserva → " + linea);
                        continue;
                    }

                    Reserva reserva = new Reserva(c[0], pasajero, vehiculo, c[3], c[4]);
                    reserva.setEstado(c[5]); // restaurar el estado guardado
                    lista.add(reserva);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar reservas: " + e.getMessage());
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