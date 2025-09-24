package com.digis01.SLeonProgramacionNCapas.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Colonia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcolonia")
    private int idColonia;
    @Column(name="nombre")
    private String nombre;
    @Column(name="codigopostal")
    private String codigoPostal;
    @ManyToOne
    @JoinColumn(name = "idmunicipio")
    public Municipio municipio;

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
     public Colonia(int IdColonia) {
        this.idColonia = IdColonia;
    }
    
    public Colonia() {
    }
  
    
    
    public int getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(int idColonia) {   
        this.idColonia = idColonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

//    public int getIdColonia() {
//        return IdColonia;
//    }
//
//    public void setIdColonia(int IdColonia) {
//        this.IdColonia = IdColonia;
//    }
//
//    public String getNombre() {
//        return Nombre;
//    }
//
//    public void setNombre(String Nombre) {
//        this.Nombre = Nombre;
//    }
//    
//    public String getCodigoPostal() {
//        return CodigoPostal;
//    }
//
//    public void setCodigoPostal(String CodigoPostal) {
//        this.CodigoPostal = CodigoPostal;
//    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Colonia(int IdColonia, String CodigoPostal, String Nombre, Municipio Municipio) {
        this.idColonia = IdColonia;
        this.codigoPostal = CodigoPostal;
        this.nombre = Nombre;
        this.municipio = Municipio;
    }
}
