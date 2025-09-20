/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.ColoniaJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.IRepositoryColonia;
import com.digis01.SLeonProgramacionNCapas.DAO.IRepositoryMunicipio;
import com.digis01.SLeonProgramacionNCapas.JPA.Colonia;
import com.digis01.SLeonProgramacionNCapas.JPA.Municipio;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="RestController de colonia", description="Controlador enfocado a metodos de la colonia")
@RestController
@RequestMapping("/coloniaapi")
public class ColoniaRestController {
    
    @Autowired
    private ColoniaJPADAOImplementation coloniaJPADAOImplementation;
    
    @Operation(
            tags = {"Colonias"},
            summary = "Obtener colonias por ID de municipio",
            description = "Devuelve una lista de colonias que pertenecen al municipio con el ID especificado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Colonias obtenidas correctamente"),
        @ApiResponse(responseCode = "404", description = "Colonia no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno al obtener las colonias")
    })
    @GetMapping("/{idMunicipio}")
    public ResponseEntity<Result> getById(@PathVariable int idMunicipio) {
        Result result;
        try {
            result = coloniaJPADAOImplementation.ColoniaByMunicipio(idMunicipio);
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
private IRepositoryMunicipio iRepositoryMunicipio;

@Autowired
private IRepositoryColonia iRepositoryColonia;

@Operation(
    tags = {"Colonias"},
    summary = "Obtener colonias por ID de municipio",
    description = "Devuelve una lista de colonias que pertenecen al municipio con el ID especificado."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Colonias obtenidas correctamente"),
    @ApiResponse(responseCode = "404", description = "Municipio no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno al obtener las colonias")
})
@GetMapping("/{idMunicipio}")
public ResponseEntity<Result> ColoniagetById(@PathVariable int idMunicipio) {
    Result result = new Result();
    try {
        
        Optional<Municipio> municipio = iRepositoryMunicipio.findById(idMunicipio);
        if(municipio.isPresent()){
            
            List<Colonia> colonias = iRepositoryColonia.findByMunicipioId(idMunicipio);

            result.correct = true;
            result.object = colonias;
            return ResponseEntity.status(200).body(result);
        } else {
            result.correct = false;
            result.errorMessage = "Municipio no encontrado";
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
