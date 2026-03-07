package org.transcesar.Dao;

import org.transcesar.Modelo.*;
import java.io.*;
import java.util.ArrayList;

public class VehiculoDao {

    public void guardarVehiculo(Vehiculo v) {
        String archivo = obtenerArchivo(v);
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(v.toString());
        } catch (IOException e) { e.printStackTrace(); }
    }

    private String obtenerArchivo(Vehiculo v) {
        if (v instanceof Buseta)   return "buseta.txt";
        if (v instanceof MicroBus) return "microbus.txt";
        return "bus.txt";
    }

    public ArrayList<Vehiculo> cargarVehiculos() {
        ArrayList<Vehiculo> lista = new ArrayList<>();
        return lista;
    }
}
