
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAOImplementation implements IUsuarioJPADAO{
    
     @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
         Result result = new Result();

        try {

            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario ORDER BY IdUsuario", Usuario.class);
            result.object = queryUsuario.getResultList();
            
            
            
            result.correct = true;
            /*Bajar a ML para renderizar*/

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    @Transactional
    public Result ADD(Usuario usuario) {
       Result result = new Result();
        try {
            entityManager.persist(usuario);
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
    public Result Update(Usuario usuario) {
        Result result = new Result();
    try {
        entityManager.merge(usuario); 
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
public Result Delete(int idUsuario) {
    Result result = new Result();
    try {
        Usuario usuarioJPA = entityManager.find(Usuario.class, idUsuario);
        
        if (usuarioJPA == null) {
            result.correct = false;
            result.errorMessage = "Usuario no encontrado";
            return result;
        }

        entityManager.remove(usuarioJPA);
        result.correct = true;
    } catch (Exception ex) {
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
        result.ex = ex;
    }
    return result;
}

    @Override
public Result GetById(int IdUsuario) {
    Result result = new Result();

    try {
        Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);

        if (usuarioJPA != null) {
            result.object = usuarioJPA;
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
    
    
    

  


