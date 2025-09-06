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
public interface IUsuarioJPADAO {
    
      Result GetAll();
      
      Result ADD(Usuario usuario);
      
      Result Update(Usuario usuario);
     
      
      Result Delete(int IdUsuario);
      
      Result GetById(int IdUsuario);
      
      Result bajalogica(int IdUsuario);
    
}
