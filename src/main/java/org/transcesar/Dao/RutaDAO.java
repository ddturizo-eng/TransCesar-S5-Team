package org.transcesar.Dao;

import org.transcesar.Modelo.Ruta;

import java.io.*;
import java.util.ArrayList;

/**
 * RutaDAO
 * Persiste y carga rutas desde rutas.txt
 * Formato: codigoRuta;ciudadOrigen;ciudadDestino;distanciaKm;tiempoEstimadoMin
 */
public class RutaDAO {

    private static final String ARCHIVO_RUTAS = "rutas.txt";

    /**
     * Escribe la ruta en rutas.txt con delimitador (;)
     */
    public void guardarRuta(Ruta r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_RUTAS, true))) {
            bw.write(r.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar ruta: " + e.getMessage());
        }
    }

    /**
     * Lee rutas.txt y reconstruye la lista de objetos Ruta.
     */
    public ArrayList<Ruta> cargarRutas() {
        ArrayList<Ruta> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_RUTAS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(";");
                // codigoRuta;ciudadOrigen;ciudadDestino;distanciaKm;tiempoEstimadoMin
                if (campos.length == 5) {
                    try {
                        double distancia = Double.parseDouble(campos[3]);
                        int    tiempo    = Integer.parseInt(campos[4]);
                        lista.add(new Ruta(campos[0], campos[1], campos[2], distancia, tiempo));
                    } catch (NumberFormatException e) {
                        System.out.println("Error al parsear ruta: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar rutas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca una ruta por su código directamente en el archivo.
     * Útil para VehiculoDAO al reconstruir objetos Vehiculo.
     */
    public Ruta buscarPorCodigo(String codigo) {
        for (Ruta r : cargarRutas()) {
            if (r.getCodigoRuta().equalsIgnoreCase(codigo)) return r;
        }
        return null;
    }
}