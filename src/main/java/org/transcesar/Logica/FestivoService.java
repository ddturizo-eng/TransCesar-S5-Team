package org.transcesar.Logica;

import org.transcesar.Modelo.Festivo;

import java.util.ArrayList;
import java.util.List;

public class FestivoService {

    private List<Festivo> festivos;

    public FestivoService() {
        festivos = new ArrayList<>();
        cargarFestivos();
    }

    private void cargarFestivos() {
        // FESTIVOS  - Año actual 2026
        festivos.add(new Festivo("2026-01-01", "Año Nuevo"));
        festivos.add(new Festivo("2026-05-01", "Día del Trabajo"));
        festivos.add(new Festivo("2026-07-20", "Día de la Independencia"));
        festivos.add(new Festivo("2026-08-07", "Batalla de Boyacá"));
        festivos.add(new Festivo("2026-12-08", "Inmaculada Concepción"));
        festivos.add(new Festivo("2026-12-25", "Navidad"));
    }
    
    public boolean esFestivo(String fecha) {
        if (fecha == null || !fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("La fecha debe estar en formato YYYY-MM-DD");
        }
        for (Festivo festivo : festivos) {
            if (festivo.getFecha().equals(fecha)) {
                return true;
            }
        }
        return false;
    }

    public List<Festivo> listarFestivos() {
        return festivos;
    }
}