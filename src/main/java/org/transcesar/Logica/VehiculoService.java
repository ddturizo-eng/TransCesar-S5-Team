package org.transcesar.Logica;

import org.transcesar.Dao.RutaDAO;
import org.transcesar.Dao.VehiculoDao;
import org.transcesar.Modelo.Vehiculo;

import java.util.ArrayList;

public class VehiculoService {

    private RutaService rutaService;
    private VehiculoDao dao;
    private ArrayList<Vehiculo> vehiculos;

    public VehiculoService(VehiculoDao dao , RutaService rutaService) {
        this.dao = dao;
        this.rutaService = rutaService;
        this.vehiculos = dao.cargarVehiculos(); // Se cargan los vehículos usando el DAO que ya recibió el RutaDAO en Main
    }

    public boolean registrarVehiculo(Vehiculo v) {
        for (Vehiculo x : vehiculos) {
            if (x.getPlaca().equalsIgnoreCase(v.getPlaca())) {
                System.out.println("ERROR: Ya existe un vehículo con la placa " + v.getPlaca());
                return false;
            }
        }
        vehiculos.add(v);
        dao.guardarVehiculo(v);
        System.out.println("Vehículo registrado exitosamente.");
        return true;
    }

    public boolean tieneCuposDisponibles(Vehiculo v) {
        return v.getPasajerosActuales() < v.getCapacidadMaxima();
    }

    public ArrayList<Vehiculo> listarVehiculos() {
        return vehiculos;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}