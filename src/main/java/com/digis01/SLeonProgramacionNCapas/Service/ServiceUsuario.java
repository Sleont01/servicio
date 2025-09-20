/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.Service;

import com.digis01.SLeonProgramacionNCapas.DAO.IRepositoryUsuario;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */
@Service
public class ServiceUsuario {
    
    @Autowired
    private IRepositoryUsuario iRepositoryUsuario;
    
    public Result BajaLogica(int idUsuario) {
        Result result = new Result();
        try {
            Optional<Usuario> usuarioOpt = iRepositoryUsuario.findById(idUsuario);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                
                // Cambiar el estado: si estaba activo (1) se vuelve inactivo (0), y viceversa
                usuario.setStatus(usuario.getStatus() == 1 ? 0 : 1);
                
                // Guardar los cambios en la base de datos
                iRepositoryUsuario.save(usuario);

                result.correct = true;
                result.object = usuario;
               
            } else {
                result.correct = false;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
        }

        return result;
    }

    
}
