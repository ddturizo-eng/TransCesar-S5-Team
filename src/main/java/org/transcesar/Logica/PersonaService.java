package org.transcesar.Logica;

import org.transcesar.Dao.PersonaDAO;
import org.transcesar.Modelo.Conductor;
import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.PasajeroAdultoMayor;
import org.transcesar.Modelo.PasajeroEstudiante;
import org.transcesar.Modelo.PasajeroRegular;

import java.util.ArrayList;

/**
 * PersonaService — MODIFICADO (Requerimiento 1, Actividad 7)
 * Al registrar un pasajero evalúa esAdultoMayor().
 * Si retorna true → crea PasajeroAdultoMayor automáticamente (30% descuento).
 * El usuario solo ingresa fecha de nacimiento, el sistema asigna la categoría.
 */
public class PersonaService {

    private PersonaDAO       personaDAO;
    private ArrayList<Conductor> conductores;
    private ArrayList<Pasajero>  pasajeros;

    public PersonaService(PersonaDAO personaDAO) {
        this.personaDAO  = personaDAO;
        this.conductores = personaDAO.cargarConductores();
        this.pasajeros   = personaDAO.cargarPasajeros();
    }

    // Conductores

    /**
     * Registra un conductor validando que tenga número y categoría de licencia.
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

    public Conductor buscarConductorPorCedula(String cedula) {
        for (Conductor c : conductores) {
            if (c.getCedula().equals(cedula)) return c;
        }
        return null;
    }

    public ArrayList<Conductor> getConductores() { return conductores; }

    // Pasajeros

    /**
     * Registra un pasajero aplicando asignación automática de categoría.
     *
     * FLUJO:
     *  1. Recibe un Pasajero temporal con la fechaNacimiento ya cargada.
     *  2. Llama esAdultoMayor() sobre ese objeto.
     *  3. Si es adulto mayor → crea PasajeroAdultoMayor automáticamente.
     *  4. Si el tipo era Estudiante o Regular, respeta esa elección.
     *
     * USO DESDE LA VISTA:
     *  - Crear un PasajeroRegular o PasajeroEstudiante con los datos ingresados.
     *  - Pasar ese objeto a registrarPasajero().
     *  - El service decide si lo promovbe a AdultoMayor.
     *
     * @param p Pasajero construido por la capa view con todos los datos.
     * @return true si se registró correctamente.
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
        if (p.getFechaNacimiento() == null || p.getFechaNacimiento().trim().isEmpty()) {
            System.out.println("ERROR: La fecha de nacimiento es obligatoria (formato YYYY-MM-DD).");
            return false;
        }

        // Asignación automática de Adulto Mayor
        Pasajero pasajeroFinal;
        if (p.esAdultoMayor()) {
            // Sin importar lo que seleccionó el usuario, el sistema asigna AdultoMayor
            pasajeroFinal = new PasajeroAdultoMayor(p.getCedula(), p.getNombre(), p.getFechaNacimiento());
            System.out.println("INFO: Pasajero con 60+ años → categoría asignada automáticamente: Adulto Mayor (30% descuento).");
        } else {
            pasajeroFinal = p; // Regular o Estudiante según lo indicado por el usuario
        }

        pasajeros.add(pasajeroFinal);
        personaDAO.guardarPasajero(pasajeroFinal);
        System.out.println("Pasajero registrado: " + pasajeroFinal.getNombre()
                + " | Descuento: " + (int)(pasajeroFinal.calcularDescuento() * 100) + "%");
        return true;
    }

    public void listarPasajeros() {
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
            return;
        }
        pasajeros.forEach(Pasajero::imprimirDetalle);
    }

    public Pasajero buscarPasajeroPorCedula(String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) return p;
        }
        return null;
    }

    public ArrayList<Pasajero> getPasajeros() { return pasajeros; }
}