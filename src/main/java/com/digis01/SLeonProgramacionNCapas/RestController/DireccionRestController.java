
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.DireccionJPADAOImplementation;
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

@RestController
@RequestMapping("direccionapi")
public class DireccionRestController {
    
    @Autowired
    private DireccionJPADAOImplementation direccionJPADAOImplementation;
    
    
    @GetMapping("/{idDireccion}")
public ResponseEntity<Result> getById(@PathVariable int idDireccion) {
    Result result;
    try {
        result = direccionJPADAOImplementation.GetById(idDireccion);
        
        return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
        result = new Result();
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        result.correct = false;
        return ResponseEntity.status(500).body(result);
    }
}

@DeleteMapping("/{idDireccion}")
public ResponseEntity delete(@PathVariable int idDireccion) {
    Result result;
    try {
        
        result = direccionJPADAOImplementation.Delete(idDireccion);
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

@PostMapping()
public ResponseEntity add(@RequestBody Usuario usuario) {
    Result result;
    try {
        result = direccionJPADAOImplementation.ADD(usuario);
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
//        result = direccionJPADAOImplementation.Update(usuario);
//        result.correct = true;
//        return ResponseEntity.status(200).body(result);
//    } catch (Exception ex) {
//        result = new Result();
//        result.ex = ex;
//        result.errorMessage = ex.getLocalizedMessage();
//        result.correct = false;
//        return ResponseEntity.status(500).body(result);
//    }
    @PutMapping("/{idDireccion}")
    public ResponseEntity update(@PathVariable int idDireccion, @RequestBody Usuario usuario) {
        Result result = new Result();
        try {

            usuario.Direcciones.get(0).setIdDireccion(idDireccion);

           
            result = direccionJPADAOImplementation.Update(usuario);

            return ResponseEntity.status(200).body(result);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.status(500).body(result);
        }
    }

} 

