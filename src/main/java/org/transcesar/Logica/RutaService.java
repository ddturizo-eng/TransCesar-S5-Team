package org.transcesar.Logica;

import org.transcesar.Dao.RutaDAO;
import org.transcesar.Modelo.Ruta;

import java.util.ArrayList;

/**
 * RutaService
 * Aplica las reglas de negocio para las rutas.
 * Valida codigo no duplicado y provee búsqueda por código.
 */
public class RutaService {

    private RutaDAO        rutaDAO;
    private ArrayList<Ruta> rutas;

    public RutaService(RutaDAO rutaDAO) {
        this.rutaDAO = rutaDAO;
        this.rutas   = rutaDAO.cargarRutas();
    }

    /**
     * Registra una ruta verificando que el codigo no esté duplicado.
     * @return true si se registró, false si el codigo ya existe.
     */
    public boolean registrarRuta(Ruta r) {
        if (buscarPorCodigo(r.getCodigoRuta()) != null) {
            System.out.println("ERROR: Ya existe una ruta con el código '"
                    + r.getCodigoRuta() + "'.");
            return false;
        }
        rutas.add(r);
        rutaDAO.guardarRuta(r);
        System.out.println("Ruta registrada exitosamente.");
        return true;
    }

    /**
     * Retorna la Ruta con ese código o null si no existe.
     */
    public Ruta buscarPorCodigo(String codigo) {
        for (Ruta r : rutas) {
            if (r.getCodigoRuta().equalsIgnoreCase(codigo)) return r;
        }
        return null;
    }

    /**
     * Retorna la lista completa de rutas registradas.
     */
    public ArrayList<Ruta> listarRutas() {
        return rutas;
    }

    public void imprimirRutas() {
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas registradas.");
            return;
        }
        rutas.forEach(Ruta::imprimirDetalle);
    }
}