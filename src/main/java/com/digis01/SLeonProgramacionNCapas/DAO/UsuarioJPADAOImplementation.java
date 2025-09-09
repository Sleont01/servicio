
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
            usuario.Direcciones.get(0).Usuario = usuario;
            entityManager.persist(usuario);
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

//    @Override
//    @Transactional
//    public Result Update(Usuario usuario) {
//        
//        Result result = new Result();
//        
//    try {
//        Usuario usuarioJPA = new Usuario(usuario.getIdUsuario());
//        entityManager.merge(usuarioJPA); 
//        result.correct = true;
//    } catch (Exception ex) {
//        result.correct = false;
//        result.errorMessage = ex.getLocalizedMessage();
//        result.ex = ex;
//    }
//    return result;
//    }
    
     @Transactional
    @Override
    public Result Update(Usuario usuario) {
        Result result = new Result();
        try {
            Usuario usuarioBD = entityManager.find(Usuario.class, usuario.getIdUsuario());
            if (usuarioBD != null) {
                usuarioBD.setNombre(usuario.getNombre());
                usuarioBD.setApellidoPaterno(usuario.getApellidoPaterno());
                usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBD.setApellidoMaterno(usuario.getApellidoMaterno());
                usuarioBD.setUsername(usuario.getUsername());
                usuarioBD.setCelular(usuario.getCelular());
                usuarioBD.setEmail(usuario.getEmail());
                usuarioBD.setPassword(usuario.getPassword());
                 usuarioBD.setSexo(usuario.getSexo());
                usuarioBD.setTelefono(usuario.getTelefono()); 
                usuarioBD.setCURP(usuario.getCURP());                
                usuarioBD.Rol.setIdRol(usuario.Rol.getIdRol());
                usuarioBD.setImagen(usuario.getImagen());
                entityManager.merge(usuarioBD);
                result.correct = true;
            } else {
                result.errorMessage = "Usuario no existe";
            }

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

    @Override
    @Transactional
    public Result bajalogica(int IdUsuario) {
         Result result = new Result();

        try {
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            if (usuarioJPA != null) {
                usuarioJPA.setStatus(usuarioJPA.getStatus()== 1 ? 0 : 1);
                entityManager.merge(usuarioJPA);
                result.correct = true;
            } else {
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
    
    
    

  


