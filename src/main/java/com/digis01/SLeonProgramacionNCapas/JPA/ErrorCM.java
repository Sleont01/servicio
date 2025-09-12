package com.digis01.SLeonProgramacionNCapas.JPA;


public class ErrorCM {
    
    public int linea;
    public String dato;
    public String mensaje;

    public ErrorCM(){}
    
    public ErrorCM(int linea, String dato, String mensaje) {
        this.linea = linea;
        this.dato = dato;
        this.mensaje = mensaje;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
