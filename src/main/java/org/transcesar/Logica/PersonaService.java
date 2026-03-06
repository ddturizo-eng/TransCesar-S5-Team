package org.transcesar.Logica;

import org.transcesar.Dao.PersonaDAO;
import org.transcesar.Modelo.Conductor;
import org.transcesar.Modelo.Pasajero;

import java.util.ArrayList;

/**
 * PersonaService
 * Aplica las reglas de negocio para Conductores y Pasajeros.
 * Regla clave: no se puede registrar un Conductor si numeroLicencia está vacío o null.
 */
public class PersonaService {

    private PersonaDAO personaDAO;
    private ArrayList<Conductor> conductores;
    private ArrayList<Pasajero>  pasajeros;

    public PersonaService(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
        this.conductores = personaDAO.cargarConductores();
        this.pasajeros   = personaDAO.cargarPasajeros();
    }

    // Conductores

    /**
     * Registra un conductor si el numeroLicencia no está vacío ni es null.
     * @return true si se registró, false si falló validación.
     */
    public boolean registrarConductor(Conductor c) {
        if (c.getNumeroLicencia() == null || c.getNumeroLicencia().trim().isEmpty()) {
            System.out.println("ERROR: No se puede registrar el conductor sin número de licencia.");
            return false;
        }
        if (c.getCategoriaLicencia() == null || c.getCategoriaLicencia().trim().isEmpty()) {
            System.out.println("ERROR: La categoría de licencia es obligatoria (B1/B2/C1/C2).");
            return false;
        }
        conductores.add(c);
        personaDAO.guardarConductor(c);
        System.out.println("Conductor registrado exitosamente.");
        return true;
    }

    public void listarConductores() {
        if (conductores.isEmpty()) {
            System.out.println("No hay conductores registrados.");
            return;
        }
        conductores.forEach(Conductor::imprimirDetalle);
    }

    public ArrayList<Conductor> getConductores() {
        return conductores;
    }

    public Conductor buscarConductorPorCedula(String cedula) {
        for (Conductor c : conductores) {
            if (c.getCedula().equals(cedula)) return c;
        }
        return null;
    }

    // Pasajeros

    /**
     * Registra un pasajero (Regular, Estudiante o AdultoMayor).
     * @return true siempre que los datos básicos sean válidos.
     */
    public boolean registrarPasajero(Pasajero p) {
        if (p.getCedula() == null || p.getCedula().trim().isEmpty()) {
            System.out.println("ERROR: La cédula del pasajero es obligatoria.");
            return false;
        }
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            System.out.println("ERROR: El nombre del pasajero es obligatorio.");
            return false;
        }
        pasajeros.add(p);
        personaDAO.guardarPasajero(p);
        System.out.println("Pasajero registrado exitosamente.");
        return true;
    }

    public void listarPasajeros() {
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
            return;
        }
        pasajeros.forEach(Pasajero::imprimirDetalle);
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public Pasajero buscarPasajeroPorCedula(String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) return p;
        }
        return null;
    }
}