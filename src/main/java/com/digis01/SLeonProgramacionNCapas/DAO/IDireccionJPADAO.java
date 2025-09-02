/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;

/**
 *
 * @author digis
 */
public interface IDireccionJPADAO {
    
    
    
    
    Result ADD(com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuarioJPA);
    
    Result Delete(int IdDireccion);
    
    Result Update(com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuarioJPA);
    
    Result GetById(int IdDireccion);
    
    
    
}
