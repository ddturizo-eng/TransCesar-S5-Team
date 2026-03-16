package org.transcesar.Modelo;

/**
 * Clase Ruta
 * Representa un trayecto formal de TransCesar con toda su información operativa.
 * Implementa Imprimible para mostrarse en consola.
 */
public class Ruta implements Imprimible {

    private String codigoRuta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distanciaKm;
    private int    tiempoEstimadoMin;

    public Ruta(String codigoRuta, String ciudadOrigen, String ciudadDestino,
                double distanciaKm, int tiempoEstimadoMin) {
        this.codigoRuta        = codigoRuta;
        this.ciudadOrigen      = ciudadOrigen;
        this.ciudadDestino     = ciudadDestino;
        this.distanciaKm       = distanciaKm;
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    // Getters y Setters

    public String getCodigoRuta() {
        return codigoRuta;
    }
    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }
    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }
    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }
    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTiempoEstimadoMin() {
        return tiempoEstimadoMin;
    }
    public void setTiempoEstimadoMin(int tiempoEstimadoMin) {
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    // Imprimible

    @Override
    public void imprimirDetalle() {
        System.out.println("========== RUTA ==========");
        System.out.println("Codigo       : " + codigoRuta);
        System.out.println("Trayecto     : " + ciudadOrigen + " → " + ciudadDestino);
        System.out.println("Distancia    : " + distanciaKm + " km");
        System.out.println("Tiempo est.  : " + tiempoEstimadoMin + " min");
        System.out.println("==========================");
    }

    @Override
    public String toString() {
        // Formato para rutas.txt: codigoRuta;ciudadOrigen;ciudadDestino;distanciaKm;tiempoEstimadoMin
        return codigoRuta + ";" + ciudadOrigen + ";" + ciudadDestino + ";"
                + distanciaKm + ";" + tiempoEstimadoMin;
    }
}