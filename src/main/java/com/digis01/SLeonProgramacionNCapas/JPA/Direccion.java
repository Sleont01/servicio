package com.digis01.SLeonProgramacionNCapas.JPA;

//import com.digis01.SLeonProgramacionNCapas.ML.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;

@Entity
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccion")
    private int IdDireccion;
    @Column(name = "calle")
    private String Calle;
    @Column(name = "numerointerior")
    private String NumeroInterior;
    @Column(name = "numeroexterior")
    private String NumeroExterior;
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    public Colonia Colonia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    @JsonIgnore
    public Usuario Usuario;
    
    
     public Direccion() {
    }

    public Direccion(int IdDireccion, String Calle, String NumeroInterior, String NumeroExterior, Colonia Colonia, Usuario Usuario) {
        this.IdDireccion = IdDireccion;
        this.Calle = Calle;
        this.NumeroInterior = NumeroInterior;
        this.NumeroExterior = NumeroExterior;
        this.Colonia = Colonia;
        this.Usuario = Usuario;
    }
     
    
    public Direccion(com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuarioJPA){
         
         com.digis01.SLeonProgramacionNCapas.JPA.Direccion direccionJPA = usuarioJPA.Direcciones.get(0);
         
         this.IdDireccion = direccionJPA.getIdDireccion();
         this.Calle = direccionJPA.getCalle();
         this.NumeroExterior = direccionJPA.getNumeroExterior();
         this.NumeroInterior = direccionJPA.getNumeroInterior();
         
         this.Colonia = new Colonia();
        this.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
        this.Usuario = new Usuario();
        this.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
    
    }


    public Direccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

  

    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public void setNumeroInterior(String NumeroInterior) {
        this.NumeroInterior = NumeroInterior;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }


    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }


    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    
    
    
}