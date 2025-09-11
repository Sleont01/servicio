/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.JPA.Colonia;
import com.digis01.SLeonProgramacionNCapas.JPA.Direccion;
import com.digis01.SLeonProgramacionNCapas.JPA.Result;
import com.digis01.SLeonProgramacionNCapas.JPA.Rol;
import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import static jakarta.persistence.GenerationType.UUID;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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



@Operation(
    tags = {"Usuarios"},
    summary = "Enviar archivo",
    description = "Se valida, genera su log, nombre de archivo, su ENUM y fecha y hora que genera"
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Realizada con éxito"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
   @PostMapping("/cargamasiva")
public ResponseEntity<?> cargamasiva(@RequestParam("file") MultipartFile file) {
    Result result = new Result();
    try {
        if (file.isEmpty()) {
            result.correct = false;
            result.errorMessage = "El archivo está vacío";

            // Generamos log para archivo inválido
            String log = String.format("sulog ||%s|%d|%s|%s",
                    file.getOriginalFilename(),
                    ValidezArchivo.INVALIDO.getValue(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    EstadoProcesamiento.NO_PROCESADO);

            return ResponseEntity.badRequest().body(result);
        }

        // === 1. Guardar archivo con nuevo nombre antes de procesar ===
        String originalName = file.getOriginalFilename();
        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        // Nuevo nombre con timestamp (puedes usar SHA-1 si prefieres)
        String nuevoNombre = "carga_" + System.currentTimeMillis() + extension;

        Path destino = Paths.get("uploads").resolve(nuevoNombre);
        Files.createDirectories(destino.getParent()); 
        Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        // === 2. Procesar archivo ya guardado ===
        byte[] fileBytes = Files.readAllBytes(destino);

        // SHA-1
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = digest.digest(fileBytes);

        // Convertir a Hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        String sha1Hex = sb.toString();

        // Generamos el log en el formato requerido
        String log = String.format("sulog %s|%s|%d|%s|%s",
                sha1Hex,
                nuevoNombre, // usamos el nuevo nombre en el log
                ValidezArchivo.VALIDO.getValue(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                EstadoProcesamiento.PROCESADO);

        // Respuesta correcta
        result.correct = true;

        return ResponseEntity.ok(result);

    } catch (Exception ex) {
        result = new Result();
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        result.correct = false;

        // Log con error
        String log = String.format("sulog ||%s|%d|%s|%s",
                file.getOriginalFilename(),
                ValidezArchivo.INVALIDO.getValue(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                EstadoProcesamiento.NO_PROCESADO);

        return ResponseEntity.status(500).body(result);
    }
}

    
    @Operation(
    tags = {"Usuarios"},
    summary = "Validar datos del archivo",
    description = "Se valida lo que contiene dentro del archivo"
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Realizada con éxito"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @PostMapping("/cargamasiva/procesar")
public ResponseEntity<?> procesarArchivo(@RequestParam("file") MultipartFile file) {
    Result result = new Result();

    try {
        if (file.isEmpty()) {
            result.correct = false;
            result.errorMessage = "El archivo está vacío";
            return ResponseEntity.badRequest().body(result);
        }

        //TXT o Excel
        String filename = file.getOriginalFilename();
        if (filename.endsWith(".txt")) {
            // Procesar archivo TXT
            List<Usuario> usuarios = parseTxt(file);
            for (Usuario usuario : usuarios) {
                try {
                    result = usuarioJPADAOImplementation.ADD(usuario);
                    result.correct = true;
                } catch (Exception ex) {
                    result = new Result();
                    result.ex = ex;
                    result.errorMessage = ex.getLocalizedMessage();
                    result.correct = false;
                    return ResponseEntity.status(500).body(result);
                }
            }
        } else if (filename.endsWith(".xlsx") || filename.endsWith(".xls")) {
            // Procesar archivo Excel
            List<Usuario> usuarios = parseExcel(file);
            for (Usuario usuario : usuarios) {
                try {
                    result = usuarioJPADAOImplementation.ADD(usuario);
                    result.correct = true;
                } catch (Exception ex) {
                    result = new Result();
                    result.ex = ex;
                    result.errorMessage = ex.getLocalizedMessage();
                    result.correct = false;
                    return ResponseEntity.status(500).body(result);
                }
            }
        } else {
            result.correct = false;
            result.errorMessage = "Formato de archivo no soportado";
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.status(201).body(result);

    } catch (Exception ex) {
        result = new Result();
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        result.correct = false;
        return ResponseEntity.status(500).body(result);
    }
}

// Métodos auxiliares para parsear TXT o Excel
private List<Usuario> parseTxt(MultipartFile file) {
    List<Usuario> usuarios = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

        String linea;
        while ((linea = bufferedReader.readLine()) != null) {
            String[] campos = linea.split("\\|");

            // Evitar errores si no hay suficientes campos
            if (campos.length < 16) {
                continue; // o registrar error
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(campos[0]);
            usuario.setApellidoPaterno(campos[1]);

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = formatter.parse(campos[2]);
                usuario.setFechaNacimiento(fechaNacimiento);
            } catch (Exception e) {
                usuario.setFechaNacimiento(null);
            }

            usuario.setApellidoMaterno(campos[3]);
            usuario.setUsername(campos[4]);
            usuario.setEmail(campos[5]);
            usuario.setPassword(campos[6]);
            usuario.setSexo(campos[7]);
            usuario.setTelefono(campos[8]);
            usuario.setCelular(campos[9]);
            usuario.setCURP(campos[10]);

            // Rol
            Rol rol = new Rol();
            try {
                rol.setIdRol(Integer.parseInt(campos[11]));
            } catch (NumberFormatException e) {
                rol.setIdRol(0);
            }
            usuario.setRol(rol);

            // Direcciones
            usuario.setDirecciones(new ArrayList<>());
            Direccion direccion = new Direccion();
            direccion.setCalle(campos[12]);
            direccion.setNumeroExterior(campos[13]);
            direccion.setNumeroInterior(campos[14]);

            Colonia colonia = new Colonia();
            try {
                colonia.setIdColonia(Integer.parseInt(campos[15]));
            } catch (NumberFormatException e) {
                colonia.setIdColonia(0);
            }
            direccion.setColonia(colonia);

            usuario.getDirecciones().add(direccion);
            usuarios.add(usuario);
        }

    } catch (Exception ex) {
        System.out.println("Error al procesar TXT: " + ex.getMessage());
        return null;
    }

    return usuarios;
}

private List<Usuario> parseExcel(MultipartFile file) {
    List<Usuario> usuarios = new ArrayList<>();

    try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        for (Row row : sheet) {
            Usuario usuario = new Usuario();
            usuario.setNombre(row.getCell(0) != null ? row.getCell(0).toString() : "");
            usuario.setApellidoPaterno(row.getCell(1) != null ? row.getCell(1).toString() : "");

            // Fecha de nacimiento
            if (row.getCell(2) != null && DateUtil.isCellDateFormatted(row.getCell(2))) {
                usuario.setFechaNacimiento(row.getCell(2).getDateCellValue());
            } else {
                usuario.setFechaNacimiento(null);
            }

            usuario.setApellidoMaterno(row.getCell(3) != null ? row.getCell(3).toString() : "");
            usuario.setUsername(row.getCell(4) != null ? row.getCell(4).toString() : "");
            usuario.setEmail(row.getCell(5) != null ? row.getCell(5).toString() : "");
            usuario.setPassword(row.getCell(6) != null ? row.getCell(6).toString() : "");
            usuario.setSexo(row.getCell(7) != null ? row.getCell(7).toString() : "");
            usuario.setTelefono(row.getCell(8) != null ? dataFormatter.formatCellValue(row.getCell(8)) : "");
            usuario.setCelular(row.getCell(9) != null ? dataFormatter.formatCellValue(row.getCell(9)) : "");
            usuario.setCURP(row.getCell(10) != null ? dataFormatter.formatCellValue(row.getCell(10)) : "");

            // Rol
            Rol rol = new Rol();
            try {
                rol.setIdRol(row.getCell(11) != null ? (int) row.getCell(11).getNumericCellValue() : 0);
            } catch (Exception e) {
                rol.setIdRol(0);
            }
            usuario.setRol(rol);

            // Direcciones
            Direccion direccion = new Direccion();
            direccion.setCalle(row.getCell(12) != null ? row.getCell(12).toString() : "");
            direccion.setNumeroExterior(row.getCell(13) != null ? row.getCell(13).toString() : "");
            direccion.setNumeroInterior(row.getCell(14) != null ? row.getCell(14).toString() : "");

            Colonia colonia = new Colonia();
            try {
                colonia.setIdColonia(row.getCell(15) != null ? (int) row.getCell(15).getNumericCellValue() : 0);
            } catch (Exception e) {
                colonia.setIdColonia(0);
            }
            direccion.setColonia(colonia);

            usuario.setDirecciones(new ArrayList<>());
            usuario.getDirecciones().add(direccion);

            usuarios.add(usuario);
        }

    } catch (Exception ex) {
        System.out.println("Error al procesar Excel: " + ex.getMessage());
        return null;
    }

    return usuarios;
}


    


    
    

    public enum ValidezArchivo {
        INVALIDO(0),
        VALIDO(1);

        private final int value;

        ValidezArchivo(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum EstadoProcesamiento {
        NO_PROCESADO,
        PROCESADO
    }
}










