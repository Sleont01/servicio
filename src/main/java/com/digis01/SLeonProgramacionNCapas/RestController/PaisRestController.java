/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;


import com.digis01.SLeonProgramacionNCapas.DAO.IRepositoryPais;
import com.digis01.SLeonProgramacionNCapas.DAO.PaisJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@Tag(name="RestController de Pais", description="Controlador enfocado al metodo de Pais")
@RestController
@RequestMapping("paisapi")
public class PaisRestController {
    
    @Autowired
    private PaisJPADAOImplementation paisJPADAOImplementation;
    
    @Operation(
            tags = {"Países"},
            summary = "Obtener todos los países",
            description = "Devuelve una lista con todos los países registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Países obtenidos correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
        @ApiResponse(responseCode = "500", description = "Error interno al obtener los países")
    })
    @GetMapping()
    public ResponseEntity GetAll(){
        Result result; 
        try {
            result = paisJPADAOImplementation.GetAll();
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
    
     @Autowired
    private IRepositoryPais iRepositorypais;
    
    @GetMapping("repository")
    @Operation(
            tags = {"Roles"},
            summary = "Obtener todos los roles",
            description = "Devuelve una lista de todos los roles disponibles en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Roles obtenidos correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
        @ApiResponse(responseCode = "500", description = "Error interno al obtener los roles")
    })
    public ResponseEntity<Result> GetAllRepository() {
        Result result = new Result();
        try {
            result.correct = true;
            result.object = iRepositorypais.findAll();
            return ResponseEntity.status(200).body(result);
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }
        
    }
    
}
