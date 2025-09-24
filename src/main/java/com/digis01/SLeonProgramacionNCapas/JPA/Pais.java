package com.digis01.SLeonProgramacionNCapas.JPA;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pais {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpais")
    private int idPais;
    @Column(name="nombre")
    private String Nombre;

    
    public Pais() {
    }
   
    
    public int getidPais() {
        return idPais;
    }

    public void setidPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
}
