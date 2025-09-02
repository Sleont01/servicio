/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Direccion;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class DireccionJPADAOImplementation implements IDireccionJPADAO {
    
    
    @Autowired
    private EntityManager entityManager;
    
    @Transactional
    @Override
    public Result Update(com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuarioJPA) {
        Result result = new Result();
       try {
           
           Direccion direccionJPA = new Direccion(usuarioJPA);
            entityManager.merge(direccionJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
        
    }

    @Override
@Transactional
public Result ADD(com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuarioJPA) {
    Result result = new Result();

    try {

            Direccion direccionJPA = new Direccion(usuarioJPA);

            entityManager.persist(direccionJPA);
            
            result.correct = true; 

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
}

        

    @Override
    @Transactional
    public Result Delete(int IdDireccion) {
        
        Result result = new Result();
    try {
        Usuario direccionJPA = entityManager.find(Usuario.class, IdDireccion);
        
        if (direccionJPA == null) {
            result.correct = false;
            result.errorMessage = "Direccion no encontrado";
            return result;
        }

        entityManager.remove(direccionJPA);
        result.correct = true;
    } catch (Exception ex) {
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
        result.ex = ex;
    }
    return result;
            }

    @Override
    public Result GetById(int IdDireccion) {
        
        Result result = new Result();

    try {
        Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);

        if (direccionJPA != null) {
            result.object = direccionJPA;
            result.correct = true;
        } else {
            result.correct = false;
            result.errorMessage = "Usuario no encontrado";
        }

    } catch (Exception ex) {
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
        result.ex = ex;
    }

    return result;
           }
    
}
    
    

        
    




