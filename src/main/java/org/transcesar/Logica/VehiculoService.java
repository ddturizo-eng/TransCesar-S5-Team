package org.transcesar.Logica;

import org.transcesar.Modelo.Vehiculo;
import org.transcesar.Dao.VehiculoDao;
import java.util.ArrayList;

public class VehiculoService {
    private VehiculoDao dao = new VehiculoDao();
    private ArrayList<Vehiculo> vehiculos = dao.cargarVehiculos();

    public boolean registrarVehiculo(Vehiculo v) {
        for (Vehiculo x : vehiculos)
            if (x.getPlaca().equals(v.getPlaca())) return false; // placa duplicada
        vehiculos.add(v);
        dao.guardarVehiculo(v);
        return true;
    }

    public boolean tieneCuposDisponibles(Vehiculo v) {
        return v.getPasajerosActuales() < v.getCapacidadMaxima();
    }

    public ArrayList<Vehiculo> listarVehiculos() {
        return vehiculos;
    }
}
