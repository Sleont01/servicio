/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.RolJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("rolapi")
public class RolRestController {
    
    @Autowired
    private RolJPADAOImplementation rolJPADAOImplementation;
    
    @GetMapping()
    public ResponseEntity GetAll(){
        Result result; 
        try {
            result = rolJPADAOImplementation.GetAll();
            result.correct = true;
            return ResponseEntity.status(200).body(result);
        } catch (Exception ex){
            result = new Result();
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            return ResponseEntity.status(500).body(result);
        }
    
    }
    
}
