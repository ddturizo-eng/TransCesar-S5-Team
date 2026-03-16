package org.transcesar.Dao;

import org.transcesar.Modelo.Buseta;
import org.transcesar.Modelo.Bus;
import org.transcesar.Modelo.MicroBus;
import org.transcesar.Modelo.Ruta;
import org.transcesar.Modelo.Vehiculo;

import java.io.*;
import java.util.ArrayList;

/**
 * VehiculoDao — MODIFICADO (Requerimiento 1, Actividad 5)
 * Al guardar: persiste el codigoRuta en el archivo.
 * Al cargar:  reconstruye el objeto Ruta completo usando RutaDAO.buscarPorCodigo().
 *
 * Formato buseta.txt  : placa;codigoRuta;capacidadMaxima;pasajerosActuales;tarifaBase
 * Formato microbus.txt: placa;codigoRuta;capacidadMaxima;pasajerosActuales;tarifaBase
 * Formato bus.txt     : placa;codigoRuta;capacidadMaxima;pasajerosActuales;tarifaBase
 */
public class VehiculoDao {

    private static final String ARCHIVO_BUSETA   = "buseta.txt";
    private static final String ARCHIVO_MICROBUS = "microbus.txt";
    private static final String ARCHIVO_BUS      = "bus.txt";

    private RutaDAO rutaDAO;

    public VehiculoDao(RutaDAO rutaDAO) {
        this.rutaDAO = rutaDAO;
    }

    // Guardar

    public void guardarVehiculo(Vehiculo v) {
        String archivo = obtenerArchivo(v);
        if (archivo == null) return;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            // toString() de Vehiculo ya serializa el codigoRuta
            bw.write(v.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar vehículo: " + e.getMessage());
        }
    }

    // Cargar

    public ArrayList<Vehiculo> cargarVehiculos() {
        ArrayList<Vehiculo> lista = new ArrayList<>();
        lista.addAll(cargarDesdeArchivo(ARCHIVO_BUSETA,   "Buseta"));
        lista.addAll(cargarDesdeArchivo(ARCHIVO_MICROBUS, "MicroBus"));
        lista.addAll(cargarDesdeArchivo(ARCHIVO_BUS,      "Bus"));
        return lista;
    }

    // Helpers

    private ArrayList<Vehiculo> cargarDesdeArchivo(String nombreArchivo, String tipo) {
        ArrayList<Vehiculo> lista = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] c = linea.split(";");
                // placa;codigoRuta;capacidadMaxima;pasajerosActuales;tarifaBase
                if (c.length == 5) {
                    // Reconstruir objeto Ruta desde el codigoRuta guardado
                    Ruta ruta = rutaDAO.buscarPorCodigo(c[1]);
                    if (ruta == null) {
                        System.out.println("Advertencia: ruta '" + c[1] + "' no encontrada para vehículo " + c[0]);
                    }

                    int    pasajerosActuales = Integer.parseInt(c[3]);
                    Vehiculo v = crearVehiculo(tipo, c[0], ruta);
                    if (v != null) {
                        v.setPasajerosActuales(pasajerosActuales);
                        lista.add(v);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar " + tipo + ": " + e.getMessage());
        }
        return lista;
    }

    private Vehiculo crearVehiculo(String tipo, String placa, Ruta ruta) {
        switch (tipo) {
            case "Buseta":   return new Buseta(placa, ruta);
            case "MicroBus": return new MicroBus(placa, ruta);
            case "Bus":      return new Bus(placa, ruta);
            default:
                System.out.println("Tipo de vehículo desconocido: " + tipo);
                return null;
        }
    }

    private String obtenerArchivo(Vehiculo v) {
        if (v instanceof Buseta)   return ARCHIVO_BUSETA;
        if (v instanceof MicroBus) return ARCHIVO_MICROBUS;
        if (v instanceof Bus)      return ARCHIVO_BUS;
        System.out.println("Tipo de vehículo no reconocido.");
        return null;
    }
}