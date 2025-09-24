/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.EstadoJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.IRepositoryEstado;
import com.digis01.SLeonProgramacionNCapas.DAO.IRepositoryPais;
import com.digis01.SLeonProgramacionNCapas.JPA.Estado;
import com.digis01.SLeonProgramacionNCapas.JPA.Pais;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@Tag(name="RestController de estado", description="Controlador enfocado al metodo de Estado")
@RestController
@RequestMapping("/estadoapi")
public class EstadoRestController {
    
    @Autowired
    private EstadoJPADAOImplementation estadoJPADAOImplementation;
    
    @Operation(
            tags = {"Estados"},
            summary = "Obtener estados por ID de país",
            description = "Devuelve una lista de estados que pertenecen al país con el ID especificado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estados obtenidos correctamente"),
        @ApiResponse(responseCode = "404", description = "Estado no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno al obtener los estados")
    })
    @GetMapping("/{idPais}")
    public ResponseEntity<Result> getById(@PathVariable int idPais) {
        Result result;
        try {
            result = estadoJPADAOImplementation.EstadoByPais(idPais);
            return ResponseEntity.status(200).body(result);
        } catch (Exception ex) {
            result = new Result();
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            return ResponseEntity.status(500).body(result);
        }
    }
    
    @Autowired
    private IRepositoryPais iRepositoryPais;
    
     @Autowired
    private IRepositoryEstado iRepositoryEstado;
     
     @Operation(
    tags = {"Estados"},
    summary = "Obtener estados por ID de país",
    description = "Devuelve una lista de estados que pertenecen al país con el ID especificado."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Estados obtenidos correctamente"),
    @ApiResponse(responseCode = "404", description = "País no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno al obtener los estados")
})
@GetMapping("repository/{idPais}")
public ResponseEntity<Result> EstadogetById(@PathVariable int idPais) {
    Result result = new Result();
    try {
       
        Optional<Estado> estado = iRepositoryEstado.findById(idPais);
        if(estado.isPresent()){
           
//            List<Estado> estados = iRepositoryEstado.findByPais_IdPais(idPais);

               // List<Estado> estados = estado.get();

            result.correct = true;
            //result.object = estados;
            return ResponseEntity.status(200).body(result);
        } else {
            result.correct = false;
            result.errorMessage = "País no encontrado";
            return ResponseEntity.status(404).body(result);
        }
    } catch (Exception ex) {
        result.correct = false;
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        return ResponseEntity.status(500).body(result);
    }
}

    
}
