/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Colonia;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class ColoniaJPADAOImplementation implements IColoniaJPADAO{
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result ColoniaByMunicipio(int IdMunicipio) {
        Result result = new Result();

        try {

            TypedQuery<Colonia> queryColonia = entityManager.createQuery("FROM Colonia WHERE Municipio.IdMunicipio = :IdMunicipio", Colonia.class);       
            queryColonia.setParameter("IdMunicipio", IdMunicipio);

            List<Colonia> colonias = queryColonia.getResultList();

            result.objects = new ArrayList<>();
            
            for (Colonia colonia : colonias) {
                result.objects.add(colonia);
            }
            
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
    }
    
    
    

