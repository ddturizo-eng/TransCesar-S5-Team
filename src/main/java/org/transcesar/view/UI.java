package org.transcesar.view;

public class UI {
    
    public static void dibujarLinea() {
        System.out.println("────────────────────────────────────────────────");
    }
    
    public static void dibujarLinea(char simbolo) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb.append(simbolo);
        }
        System.out.println(sb.toString());
    }
    
    public static void exito(String mensaje) {
        System.out.println("[✔] " + mensaje);
    }
    
    public static void error(String mensaje) {
        System.out.println("[✘] " + mensaje);
    }
    
    public static void info(String mensaje) {
        System.out.println("[ℹ] " + mensaje);
    }
    
    public static void titulo(String texto) {
        int ancho = 50;
        int espacios = (ancho - texto.length() - 2) / 2;
        StringBuilder sb = new StringBuilder();
        sb.append("╔");
        for (int i = 0; i < ancho; i++) sb.append("═");
        sb.append("╗\n");
        
        sb.append("║");
        for (int i = 0; i < espacios; i++) sb.append(" ");
        sb.append(texto);
        for (int i = 0; i < ancho - espacios - texto.length(); i++) sb.append(" ");
        sb.append("║\n");
        
        sb.append("╚");
        for (int i = 0; i < ancho; i++) sb.append("═");
        sb.append("╝");
        
        System.out.println(sb.toString());
    }
    
    public static void marco(String titulo, String[] opciones) {
        int ancho = 50;
        
        System.out.print("╔");
        for (int i = 0; i < ancho; i++) System.out.print("═");
        System.out.println("╗");
        
        int espacios = (ancho - titulo.length() - 2) / 2;
        System.out.print("║");
        for (int i = 0; i < espacios; i++) System.out.print(" ");
        System.out.print(titulo);
        for (int i = 0; i < ancho - espacios - titulo.length(); i++) System.out.print(" ");
        System.out.println("║");
        
        System.out.print("╠");
        for (int i = 0; i < ancho; i++) System.out.print("═");
        System.out.println("╣");
        
        for (String opcion : opciones) {
            System.out.print("║  ");
            System.out.print(opcion);
            for (int i = 0; i < ancho - opcion.length() - 3; i++) System.out.print(" ");
            System.out.println("║");
        }
        
        System.out.print("╚");
        for (int i = 0; i < ancho; i++) System.out.print("═");
        System.out.println("╝");
    }
}
