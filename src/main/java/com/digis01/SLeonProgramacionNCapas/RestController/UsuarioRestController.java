/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
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

/**
 *
 * @author digis
 */
@Tag(name="RestController de usuario", description="Controlador enfocado a metodos del Usuario")
@RestController
@RequestMapping("usuarioapi")
public class UsuarioRestController {


     @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;

    @Operation(tags = {"Usuarios"},
            summary = "Obtener todos los usuarios", description = "Metdo para retornar todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios obtenidos correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
        @ApiResponse(responseCode = "500", description = "Error interno al obtener los usuarios")
    })
    @GetMapping()
    public ResponseEntity GetAll() {
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
    @Operation(
    tags = {"Usuarios"},
    summary = "Agregar un nuevo usuario",
    description = "Crea un nuevo usuario con los datos proporcionados"
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
    @ApiResponse(responseCode = "500", description = "Error interno al agregar el usuario")
})
    @PostMapping()
public ResponseEntity add(@RequestBody Usuario usuario) {
    Result result;
    try {
        usuario.getIdUsuario();
        result = usuarioJPADAOImplementation.ADD(usuario);
        result.correct = true;
        return ResponseEntity.status(201).body(result); //200
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

@Operation(
    tags = {"Usuarios"},
    summary = "Actualizar un usuario por ID",
    description = "Actualiza los datos de un usuario existente utilizando su ID y debe contener los nuevos datos del usuario")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno al actualizar el usuario")
})
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

@Operation(
    tags = {"Usuarios"},
    summary = "Eliminar un usuario por ID",
    description = "Elimina un usuario según su ID."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno al eliminar el usuario")
})
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

@Operation(
    tags = {"Usuarios"},
    summary = "Obtener usuario por ID",
    description = "Devuelve la información de un usuario específico identificado por su ID."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno al obtener el usuario")
})
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

 
    @Operation(
    tags = {"Usuarios"},
    summary = "Dar de baja lógica a un usuario por su ID",
    description = "Este endpoint realiza una baja lógica (desactivación) de un usuario específico."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Baja lógica realizada con éxito"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @PutMapping("/status/{idUsuario}")
public ResponseEntity bajalogica(@PathVariable int idUsuario) {
    Result result;

    try {
        result = usuarioJPADAOImplementation.bajalogica(idUsuario);
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








