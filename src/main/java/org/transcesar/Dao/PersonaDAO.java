package org.transcesar.Dao;

import org.transcesar.Modelo.Conductor;
import org.transcesar.Modelo.Pasajero;
import org.transcesar.Modelo.PasajeroAdultoMayor;
import org.transcesar.Modelo.PasajeroEstudiante;
import org.transcesar.Modelo.PasajeroRegular;

import java.io.*;
import java.util.ArrayList;

/**
 * PersonaDAO
 * Responsable de leer y escribir conductores.txt y pasajeros.txt
 * Formato conductor  : cedula;nombre;numeroLicencia;categoriaLicencia
 * Formato pasajero   : cedula;nombre;tipo  (Regular | Estudiante | AdultoMayor)
 */
public class PersonaDAO {

    private static final String ARCHIVO_CONDUCTORES = "conductores.txt";
    private static final String ARCHIVO_PASAJEROS   = "pasajeros.txt";

    // Conductores

    public void guardarConductor(Conductor c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CONDUCTORES, true))) {
            bw.write(c.getCedula() + ";" + c.getNombre() + ";" +
                    c.getNumeroLicencia() + ";" + c.getCategoriaLicencia());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar conductor: " + e.getMessage());
        }
    }

    public ArrayList<Conductor> cargarConductores() {
        ArrayList<Conductor> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_CONDUCTORES);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(";");
                // cedula;nombre;numeroLicencia;categoriaLicencia
                if (campos.length == 4) {
                    lista.add(new Conductor(campos[0], campos[1], campos[2], campos[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conductores: " + e.getMessage());
        }
        return lista;
    }

    // Pasajeros

    public void guardarPasajero(Pasajero p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PASAJEROS, true))) {
            // toString() de cada subclase ya tiene el formato cedula;nombre;tipo
            bw.write(p.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar pasajero: " + e.getMessage());
        }
    }

    public ArrayList<Pasajero> cargarPasajeros() {
        ArrayList<Pasajero> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_PASAJEROS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(";");
                // cedula;nombre;tipo
                if (campos.length == 3) {
                    String tipo = campos[2].trim();
                    switch (tipo) {
                        case "Regular":
                            lista.add(new PasajeroRegular(campos[0], campos[1]));
                            break;
                        case "Estudiante":
                            lista.add(new PasajeroEstudiante(campos[0], campos[1]));
                            break;
                        case "AdultoMayor":
                            lista.add(new PasajeroAdultoMayor(campos[0], campos[1]));
                            break;
                        default:
                            System.out.println("Tipo de pasajero desconocido: " + tipo);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pasajeros: " + e.getMessage());
        }
        return lista;
    }
}