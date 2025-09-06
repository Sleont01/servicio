/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("usuarioapi")
public class UsuarioRestController {


     @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;


    
     @GetMapping()
    public ResponseEntity GetAll(){
        Result result; 
        try {
            result = usuarioJPADAOImplementation.GetAll();
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
    
    @PostMapping()
public ResponseEntity add(@RequestBody Usuario usuario) {
    Result result;
    try {
        usuario.getIdUsuario();
        result = usuarioJPADAOImplementation.ADD(usuario);
        result.correct = true;
        return ResponseEntity.status(200).body(result); 
    } catch (Exception ex) {
        result = new Result();
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        result.correct = false;
        return ResponseEntity.status(500).body(result);
    }
}

//@PatchMapping()
//public ResponseEntity update(@RequestBody Usuario usuario) {
//    Result result;
//    try {
//        result = usuarioJPADAOImplementation.Update(usuario);
//        result.correct = true;
//        return ResponseEntity.status(200).body(result);
//    } catch (Exception ex) {
//        result = new Result();
//        result.ex = ex;
//        result.errorMessage = ex.getLocalizedMessage();
//        result.correct = false;
//        return ResponseEntity.status(500).body(result);
//    }
//    
//  
//}

@PutMapping("/{idUsuario}")
public ResponseEntity update(@PathVariable int idUsuario, @RequestBody Usuario usuario) {
    usuario.setIdUsuario(idUsuario); 

    Result result;
    try {
        result = usuarioJPADAOImplementation.Update(usuario);
        result.correct = true;
        return ResponseEntity.status(200).body(result); 
    } catch (Exception ex) {
        result = new Result();
        result.correct = false;
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        return ResponseEntity.status(500).body(result); 
    }
}


@DeleteMapping("/{idUsuario}")
public ResponseEntity delete(@PathVariable int idUsuario) {
    Result result;
    try {
        
        result = usuarioJPADAOImplementation.Delete(idUsuario);
        result.correct = true;
        return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
        result = new Result();
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        result.correct = false;
        return ResponseEntity.status(500).body(result);
    }

}

@GetMapping("/{idUsuario}")
public ResponseEntity<Result> getById(@PathVariable int idUsuario) {
    Result result;
    try {
        result = usuarioJPADAOImplementation.GetById(idUsuario);
        
        return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
        result = new Result();
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        result.correct = false;
        return ResponseEntity.status(500).body(result);
    }
}

 
    
    @PutMapping("/status/{IdUsuario}")
public ResponseEntity bajalogica(@PathVariable int IdUsuario) {
    Result result;

    try {
        result = usuarioJPADAOImplementation.bajalogica(IdUsuario);
        return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
        result = new Result();
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        result.correct = false;
        return ResponseEntity.status(500).body(result);
    }
}




}








