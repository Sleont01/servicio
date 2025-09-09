/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.MunicipioJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="RestController de Municipio", description="Controlador enfocado al metodo de Municipio")
@RestController
@RequestMapping("/municipioapi")
public class MunicipioRestController {
    
    @Autowired
    private MunicipioJPADAOImplementation municipioJPADAOImplementation;
    
    @Operation(
            tags = {"Municipios"},
            summary = "Obtener municipios por ID de estado",
            description = "Devuelve una lista de municipios que pertenecen al estado con el ID especificado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Municipios obtenidos correctamente"),
        @ApiResponse(responseCode = "404", description = "Municipio no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno al obtener los municipios")
    })
    @GetMapping("/{idEstado}")
    public ResponseEntity<Result> getById(@PathVariable int idEstado) {
        Result result;
        try {
            result = municipioJPADAOImplementation.MunicipioByEstado(idEstado);
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
