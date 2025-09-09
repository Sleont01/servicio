
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.DireccionJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name="RestController de direccion", description="Controlador enfocado a metodos del Direccion")
@RestController
@RequestMapping("direccionapi")
public class DireccionRestController {
    
    @Autowired
    private DireccionJPADAOImplementation direccionJPADAOImplementation;
    
    @Operation(
    tags = {"Direcciones"},
    summary = "Obtener dirección por ID",
    description = "Devuelve la información de una dirección específica mediante su ID."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Dirección obtenida correctamente"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno al obtener la dirección")
})
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

@Operation(
    tags = {"Direcciones"},
    summary = "Eliminar una dirección por ID",
    description = "Elimina una dirección específica del sistema usando su ID"
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Dirección eliminada correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    @ApiResponse(responseCode = "404", description = "Direccion no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno al eliminar la dirección")
})
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

@Operation(
    tags = {"Direcciones"},
    summary = "Agregar una nueva dirección",
    description = "Crea una nueva dirección utilizando los datos enviados en el cuerpo de la solicitud."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Direccion creado correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
    @ApiResponse(responseCode = "500", description = "Error interno al agregar la dirección")
})
@PostMapping()
public ResponseEntity add(@RequestBody Usuario usuario) {
    Result result;
    try {
        result = direccionJPADAOImplementation.ADD(usuario);
        result.correct = true;
        return ResponseEntity.status(201).body(result); 
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

    @Operation(
            tags = {"Direcciones"},
            summary = "Actualizar una dirección por ID",
            description = "Actualiza la información de una dirección específica. La dirección debe estar incluida dentro del objeto Usuario enviado en el cuerpo de la solicitud."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos de direccion"),
        @ApiResponse(responseCode = "404", description = "Direccion no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno al actualizar la dirección")
    })
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

