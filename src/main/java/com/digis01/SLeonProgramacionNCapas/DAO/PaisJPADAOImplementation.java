/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Pais;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class PaisJPADAOImplementation implements IPaisJPADAO{
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
         Result result = new Result();

        try {

            TypedQuery<Pais> queryPais = entityManager.createQuery("FROM Pais ORDER BY IdPais", Pais.class);
            result.object = queryPais.getResultList();
            
            
            
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
