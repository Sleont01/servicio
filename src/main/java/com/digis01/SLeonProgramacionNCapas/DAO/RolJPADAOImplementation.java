/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import com.digis01.SLeonProgramacionNCapas.JPA.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class RolJPADAOImplementation implements IRolJPADAO{
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
         Result result = new Result();

        try {

            TypedQuery<Rol> queryRol = entityManager.createQuery("FROM Rol ORDER BY IdRol", Rol.class);
            result.object = queryRol.getResultList();
            
            
            
            result.correct = true;
            /*Bajar a ML para renderizar*/

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
}
