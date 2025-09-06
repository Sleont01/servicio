/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Colonia;
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
//    
//    @Transactional
//    @Override
//    public Result Update(com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuarioJPA) {
//        Result result = new Result();
//       try {
//          
//           Direccion direccionJPA = new Direccion(usuarioJPA);
//            entityManager.merge(direccionJPA);
//            result.correct = true;
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//
//        return result;
//        
//    }
//    
    
    @Transactional
    @Override
    public Result Update(com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuarioJPA) {
        Result result = new Result();
       try {
           com.digis01.SLeonProgramacionNCapas.JPA.Direccion direccionBD = entityManager.find(Direccion.class, usuarioJPA.Direcciones.get(0).getIdDireccion());
            if (direccionBD != null) {
                Direccion direccion = usuarioJPA.Direcciones.get(0);

                Usuario usuarioRef = entityManager.getReference(Usuario.class, usuarioJPA.getIdUsuario());
                Colonia coloniaRef = entityManager.getReference(Colonia.class, direccion.Colonia.getIdColonia());

                direccionBD.setCalle(direccion.getCalle());
                direccionBD.setNumeroInterior(direccion.getNumeroInterior());
                direccionBD.setNumeroExterior(direccion.getNumeroExterior());
                direccionBD.setUsuario(usuarioRef);
                direccionBD.setColonia(coloniaRef);
                entityManager.merge(direccionBD);
                result.correct = true;
              
            } else {
               
                result.errorMessage = "Direccion no existe";
            }
//           Direccion direccionJPA = new Direccion(usuarioJPA);
//            entityManager.merge(direccionJPA);
//            result.correct = true;
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
        Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);
        
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
    
    

        
    




