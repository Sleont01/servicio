/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.DAO.IRepositoryUsuario;
import com.digis01.SLeonProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis01.SLeonProgramacionNCapas.JPA.Colonia;
import com.digis01.SLeonProgramacionNCapas.JPA.Direccion;
import com.digis01.SLeonProgramacionNCapas.JPA.ErrorCM;
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
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.status;
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
import static org.springframework.web.servlet.function.ServerResponse.status;

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
    List<Usuario> usuarios = new ArrayList<>();
    List<ErrorCM> errores = new ArrayList<>();

    try {
        if (file.isEmpty()) {
            result.correct = false;
            result.errorMessage = "El archivo está vacío";

            // Log archivo inválido
            String log = String.format("sulog ||%s|%d|%s|%s",
                    file.getOriginalFilename(),
                    ValidezArchivo.INVALIDO.getValue(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    EstadoProcesamiento.NO_PROCESADO);

            return ResponseEntity.badRequest().body(result);
        }

        // Validar extensión
        String originalName = file.getOriginalFilename();
        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf(".") + 1);
        }

        boolean valid = extension.equalsIgnoreCase("txt") || extension.equalsIgnoreCase("xlsx");
        if (!valid) {
            errores.add(new ErrorCM(1, "", "Tipo de Archivo Invalido"));
            result.correct = false;
            result.errorMessage = "Formato no soportado. Solo se aceptan TXT y XLSX.";
            return ResponseEntity.status(400).body(result);
        }

        // Guardar archivo con nuevo nombre
        String upDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String nuevoNombre = "carga_" + upDate + "_" + originalName;

        Path destino = Paths.get("uploads").resolve(nuevoNombre);
        Files.createDirectories(destino.getParent());
        Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        //Generar SHA-1 del contenido
        byte[] fileBytes = Files.readAllBytes(destino);
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = digest.digest(fileBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        String sha1Hex = sb.toString();

        // Procesar archivo
        if (extension.equalsIgnoreCase("txt")) {
            usuarios = ProcesarTXT(destino.toFile());
        } else if (extension.equalsIgnoreCase("xlsx")) {
            usuarios = ProcesarExcel(destino.toFile());
        }

        // Validar datos
        errores = ValidarDatos(usuarios);

        // Ruta log
        String logsCM = System.getProperty("user.dir") + "/src/main/resources/files/logs/logsCM.txt";
        Files.createDirectories(Paths.get(logsCM).getParent());
        
        if (errores != null && !errores.isEmpty()) {
            try (FileWriter fw = new FileWriter(logsCM, true); PrintWriter writer = new PrintWriter(fw)) {
                String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.println("log|" + sha1Hex + "|" + nuevoNombre + "|" + status.ERROR.ordinal() + "|" + timeStamp + "|" + "Archivo con errores");
            }
            return ResponseEntity.status(400).body(errores);
        } else {
            try (FileWriter fw = new FileWriter(logsCM, true); PrintWriter writer = new PrintWriter(fw)) {
                String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.println("log|" + sha1Hex + "|" + nuevoNombre + "|" + status.PROCESAR.ordinal() + "|" + timeStamp + "|" + "Archivo listo para procesar");
            }
        }

        result.correct = true;
        result.object = sha1Hex;
        return ResponseEntity.ok(result);

    } catch (Exception ex) {
        result = new Result();
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        result.correct = false;

        // Log error
        String log = String.format("sulog ||%s|%d|%s|%s",
                file.getOriginalFilename(),
                ValidezArchivo.INVALIDO.getValue(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                EstadoProcesamiento.NO_PROCESADO);

        return ResponseEntity.status(500).body(result);
    }
}
 public enum status {
        PROCESAR, ERROR, PROCESADO
    }

   

@GetMapping("/cargamasiva/procesar/{sha1Hex}")
public ResponseEntity<Result> procesarCargaMasiva(@PathVariable("sha1Hex") String sha1Hex) {
    Result result = new Result();
    try {
        // Ruta del log
        String logsCM = System.getProperty("user.dir") + "/src/main/resources/files/logs/logsCM.txt";
        Path logPath = Paths.get(logsCM);
        if (!Files.exists(logPath)) {
            result.correct = false;
            result.errorMessage = "No se encontró el archivo de logs.";
            return ResponseEntity.status(400).body(result);
        }

        // Buscar coincidencia en el log y verificar status = 1 (PROCESAR)
        boolean archivoValido = Files.lines(logPath)
                .anyMatch(line -> line.contains(sha1Hex) && line.contains("|1|"));

        if (!archivoValido) {
            result.correct = false;
            result.errorMessage = "Archivo no encontrado o no tiene status válido para procesar.";
            return ResponseEntity.status(400).body(result);
        }

        // Construir ruta completa del archivo en uploads
        Path archivoPath = Paths.get("uploads").resolve(sha1Hex);
        if (!Files.exists(archivoPath)) {
            result.correct = false;
            result.errorMessage = "Archivo físico no encontrado en uploads.";
            return ResponseEntity.status(400).body(result);
        }

        // Obtener extensión
        String extension = "";
        String originalName = sha1Hex;
        if (originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf(".") + 1);
        }

        List<Usuario> usuarios = new ArrayList<>();
        // Procesar según extensión
        if (extension.equalsIgnoreCase("txt")) {
            usuarios = ProcesarTXT(archivoPath.toFile());
        } else if (extension.equalsIgnoreCase("xlsx")) {
            usuarios = ProcesarExcel(archivoPath.toFile());
        } else {
            result.correct = false;
            result.errorMessage = "Formato de archivo no soportado.";
            return ResponseEntity.status(400).body(result);
        }

        // Guardar cada usuario en BD
        for (Usuario usuario : usuarios) {
            usuario.getIdUsuario();
            result = usuarioJPADAOImplementation.ADD(usuario);
        }

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
    
        private List<Usuario> ProcesarTXT(File file){
        
        try {
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            
            String linea = ""; 
            List<Usuario> usuarios = new ArrayList<>();
            while ((linea = bufferedReader.readLine()) != null) {                
                String[] campos = linea.split("\\|");
                Usuario usuario = new Usuario();
                usuario.setNombre(campos[0]);
                usuario.setApellidoPaterno(campos[1]);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = formatter.parse(campos[2]);
                usuario.setFechaNacimiento(fechaNacimiento);
                usuario.setApellidoMaterno(campos[3]);
                usuario.setUsername(campos[4]);
                usuario.setEmail(campos[5]);
                usuario.setPassword(campos[6]);
                usuario.setSexo(campos[7]);
                usuario.setTelefono(campos[8]);
                usuario.setCelular(campos[9]);
                usuario.setCURP(campos[10]);
                usuario.Rol = new Rol();
                try {
                    usuario.Rol.setIdRol(Integer.parseInt(campos[11]));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    usuario.Rol.setIdRol(0);
                }

                usuario.setDirecciones(new ArrayList<>());
                Direccion direccion = new Direccion();
                direccion.setCalle(campos[12]);
                direccion.setNumeroExterior(campos[13]);
                direccion.setNumeroInterior(campos[14]);

                Colonia colonia = new Colonia();
                try {
                    colonia.setIdColonia(Integer.parseInt(campos[15]));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    colonia.setIdColonia(0);
                }
                direccion.setColonia(colonia);

                usuario.getDirecciones().add(direccion);
                usuarios.add(usuario);

            }
            return usuarios;
        } catch (Exception ex) {
            System.out.println("error");
            return null;
        }

    }
    private List<Usuario> ProcesarExcel(File file) {

        List<Usuario> usuarios = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Usuario usuario = new Usuario();
                usuario.setNombre(row.getCell(0) != null ? row.getCell(0).toString() : "");
                usuario.setApellidoPaterno(row.getCell(1) != null ? row.getCell(1).toString() : "");
                // usuario.setFechaNacimiento(row.getCell(2).getDateCellValue());
               usuario.setFechaNacimiento(row.getCell(2) != null && DateUtil.isCellDateFormatted(row.getCell(2)) ? row.getCell(2).getDateCellValue() : null);
                usuario.setApellidoMaterno(row.getCell(3) != null ?  row.getCell(3).toString() : "");
                usuario.setUsername(row.getCell(4) != null ?  row.getCell(4).toString() : "");
                usuario.setEmail(row.getCell(5) != null ?  row.getCell(5).toString() : "");
                usuario.setPassword(row.getCell(6) != null ?  row.getCell(6).toString() : "");
                usuario.setSexo(row.getCell(7) != null ?  row.getCell(7).toString() : "");
                DataFormatter dataFormatter = new DataFormatter();
                usuario.setTelefono(row.getCell(8) != null ? dataFormatter.formatCellValue(row.getCell(8)):"");
                usuario.setCelular(row.getCell(9) != null ? dataFormatter.formatCellValue(row.getCell(9)):"");
                usuario.setCURP(row.getCell(10) != null ? dataFormatter.formatCellValue(row.getCell(10)):"");
                
                usuario.Rol = new Rol();
                usuario.Rol.setIdRol(row.getCell(11) != null ? (int) row.getCell(11).getNumericCellValue() : 0);
                
                usuario.setDirecciones(new ArrayList<>());
                Direccion direccion = new Direccion();
                direccion.setCalle(row.getCell(12) != null ? row.getCell(12).toString() : "");
                direccion.setNumeroExterior(row.getCell(13) != null ? row.getCell(13).toString() : "");
                direccion.setNumeroInterior(row.getCell(14) != null ? row.getCell(14).toString() : "");
                
                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(row.getCell(15) != null ? (int) row.getCell(15).getNumericCellValue() : 0);
                
                usuario.Direcciones.add(direccion);
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (Exception ex){
            System.out.println("error");
            return null;
        }
    }
    
    private List<ErrorCM> ValidarDatos(List<Usuario> usuarios) {
        List<ErrorCM> errores = new ArrayList<>();

        String soloLetrasPattern = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        String soloNumerosPattern = "^[0-9]+$";

        int linea = 1;
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre() == null || usuario.getNombre().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getNombre() == null ? "null" : "vacio", "Nombre es un campo obligatorio"));
            } else if (!usuario.getNombre().matches(soloLetrasPattern)) {
                errores.add(new ErrorCM(linea, usuario.getNombre(), "Nombre solo puede contener letras"));
            }
            if (usuario.getApellidoPaterno() == null || usuario.getApellidoPaterno().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getApellidoPaterno() == null ? "null" : "vacio", "Apellido paterno es obligatorio"));
            } else if (!usuario.getApellidoPaterno().matches(soloLetrasPattern)) {
                errores.add(new ErrorCM(linea, usuario.getApellidoPaterno(), "Apellido paterno solo puede contener letras"));
            }

            if (usuario.getFechaNacimiento() == null) {
                errores.add(new ErrorCM(linea, "", "Fecha de nacimiento es obligatoria"));
            }

            if (usuario.getApellidoMaterno() == null || usuario.getApellidoMaterno().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getApellidoMaterno() == null ? "null" : "vacio", "Apellido materno es obligatorio"));
            } else if (!usuario.getApellidoMaterno().matches(soloLetrasPattern)) {
                errores.add(new ErrorCM(linea, usuario.getApellidoMaterno(), "Apellido materno solo puede contener letras"));
            }

            if (usuario.getUsername() == null || usuario.getUsername().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getUsername()== null ? "null" : "vacio", "Username es obligatorio"));
            }

            if (usuario.getEmail() == null || usuario.getEmail().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getEmail()== null ? "null" : "vacio", "Email es obligatorio"));
            } else if (!usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                errores.add(new ErrorCM(linea, usuario.getEmail(), "Formato de email no válido"));
            }

            if (usuario.getPassword() == null || usuario.getPassword().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getPassword()== null ? "null" : "vacio", "Password es obligatorio"));
            }

            if (usuario.getSexo() == null || usuario.getSexo().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getSexo()== null ? "null" : "vacio", "Sexo es obligatorio"));
            }

            if (usuario.getTelefono() == null || usuario.getTelefono().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getTelefono()== null ? "null" : "vacio", "Teléfono es obligatorio"));
            } else if (!usuario.getTelefono().matches(soloNumerosPattern)) {
                errores.add(new ErrorCM(linea, usuario.getTelefono(), "Teléfono solo puede contener números"));
            }

            if (usuario.getCelular() == null || usuario.getCelular().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getCelular()== null ? "null" : "vacio", "Celular es obligatorio"));
            } else if (!usuario.getCelular().matches(soloNumerosPattern)) {
                errores.add(new ErrorCM(linea, usuario.getCelular(), "Celular solo puede contener números"));
            }

            if (usuario.getCURP() == null || usuario.getCURP().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getCURP()== null ? "null" : "vacio", "CURP es obligatorio"));
            }
            if (usuario.Rol.getIdRol() == 0) {
                errores.add(new ErrorCM(linea, usuario.Rol.getIdRol() + "", "Numero de rol no valido"));
            }
            
            if(usuario.Direcciones == null || usuario.Direcciones.isEmpty()){
                errores.add(new ErrorCM(linea, "0", "Debe tener al menos una direccion"));
            } else{
                Direccion direccion = usuario.Direcciones.get(0);
                
                if(direccion.getCalle() == null || direccion.getCalle().trim().equals("")){
                    errores.add(new ErrorCM(linea, direccion.getCalle() == null ? "null" : "vacio", "Calle es obligatoria"));
                }
                if(direccion.getNumeroExterior()== null || direccion.getNumeroExterior().trim().equals("")){
                    errores.add(new ErrorCM(linea, direccion.getNumeroExterior() == null ? "null" : "vacio", "Numero exterior obligatoria"));
                }
                if (direccion.getNumeroInterior() == null || direccion.getNumeroInterior().trim().equals("")) {
                    errores.add(new ErrorCM(linea, direccion.getNumeroInterior() == null ? "null" : "vacio", "Numero interior obligatoria"));
                }
                if (direccion.getNumeroInterior() == null || direccion.getNumeroInterior().trim().equals("")) {
                    errores.add(new ErrorCM(linea, direccion.getNumeroInterior() == null ? "null" : "vacio", "Numero interior obligatoria"));
                }
                if (direccion.Colonia == null) {
                    errores.add(new ErrorCM(linea, "null", "Colonia es obligatoria"));
                } else if (direccion.Colonia.getIdColonia() == 0) {
                    errores.add(new ErrorCM(linea, "0", "ID de colonia no válido"));
                }

            }

            linea++;
        }

        return errores;
    }
    
     @Autowired
    private IRepositoryUsuario iRepositoryUsuario;
    

    @GetMapping("repository")
    @Operation(
            tags = {"Usuarios"},
            summary = "Obtener todos los usuarios",
            description = "Método para retornar todos los usuarios"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios obtenidos correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
        @ApiResponse(responseCode = "500", description = "Error interno al obtener los usuarios")
    })
    public ResponseEntity<Result> GetAllRepository() {
        Result result = new Result();
        try {
            result.correct = true;
            result.object = iRepositoryUsuario.findAll();
            return ResponseEntity.status(200).body(result);
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }

    }
    
    @PostMapping("repository")
@Operation(
        tags = {"Usuarios"},
        summary = "Agregar un nuevo usuario",
        description = "Método para agregar un nuevo usuario a la base de datos"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario agregado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos del usuario"),
        @ApiResponse(responseCode = "500", description = "Error interno al agregar el usuario")
})
public ResponseEntity<Result> AddUsuario(@RequestBody Usuario usuario) {
    Result result = new Result();
    try {
        
        Usuario nuevoUsuario = iRepositoryUsuario.save(usuario);

        result.correct = true;
        result.object = nuevoUsuario;

      
        return ResponseEntity.status(201).body(result);
    } catch (Exception ex) {
        result.correct = false;
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
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
@GetMapping("repository/{idUsuario}") 
public ResponseEntity<Result> getByIdUsuario(@PathVariable int idUsuario) {
    Result result = new Result();  
    try {
        
        Optional<Usuario> usuarioOpt = iRepositoryUsuario.findById(idUsuario);

        if (usuarioOpt.isPresent()) {
            result.correct = true;
            result.object = usuarioOpt.get();
            return ResponseEntity.status(200).body(result);
        } else {
            result.correct = false;
            result.errorMessage = "Usuario no encontrado";
            return ResponseEntity.status(404).body(result);
        }

    } catch (Exception ex) {
        result.correct = false;
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        return ResponseEntity.status(500).body(result);
    }
}

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
@PutMapping("/repository/{idUsuario}")
public ResponseEntity actualizar(@PathVariable int idUsuario, @RequestBody Usuario usuario) {
    usuario.setIdUsuario(idUsuario); 

         Result result = new Result();

    try {
        // Buscar el usuario existente
        Optional<Usuario> usuarioFind = iRepositoryUsuario.findById(idUsuario);

        if (usuarioFind.isPresent()) {
            // Actualizar el ID por si viene diferente
            usuario.setIdUsuario(idUsuario);

            // Guardar los cambios
            Usuario actualizado = iRepositoryUsuario.save(usuario);

            result.correct = true;
            result.object = actualizado;
            return ResponseEntity.status(200).body(result);
        } else {
            // Usuario no encontrado
            result.correct = false;
            result.errorMessage = "Usuario con ID " + idUsuario + " no encontrado";
            return ResponseEntity.status(404).body(result);
        }

    } catch (Exception ex) {
        result.correct = false;
        result.ex = ex;
        result.errorMessage = ex.getLocalizedMessage();
        return ResponseEntity.status(500).body(result);
    }
//    Result result;
//    try {
//        // Guardamos directamente el usuario usando save()
//        result = new Result();
//        Usuario actualizado = iRepositoryUsuario.save(usuario);
//        result.correct = true;
//        result.object = actualizado;
//        return ResponseEntity.status(200).body(result); 
//
//    } catch (Exception ex) {
//        result = new Result();
//        result.correct = false;
//        result.ex = ex;
//        result.errorMessage = ex.getLocalizedMessage();
//        return ResponseEntity.status(500).body(result); 
//    }
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
@DeleteMapping("repository/{idUsuario}")
public ResponseEntity deleteusuario(@PathVariable int idUsuario) {
    Result result;
    try {
 
        iRepositoryUsuario.deleteById(idUsuario);

        result = new Result();
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




}










