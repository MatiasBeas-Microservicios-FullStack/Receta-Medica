package cl.koz.receta_medica.controller;

import cl.koz.receta_medica.DTO.RecetaMedicaRequestDTO;
import cl.koz.receta_medica.DTO.RecetaMedicaResponseDTO;
import cl.koz.receta_medica.service.RecetaMedicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RecetaMedica")
@Tag(name = "Receta Medica", description = "Operaciones relacionadas con las Recetas Medicas")
@RequiredArgsConstructor
public class RecetaMedicaController {
    private final RecetaMedicaService recetaMedicaService;

    @GetMapping
    @Operation(summary = "Obtener todas las Recetas Medicas", description = "Entrega una lista de todas las Recetas Medicas registradas")
    public ResponseEntity<List<RecetaMedicaResponseDTO>> obtenerTodoRM(){
        return ResponseEntity.ok(recetaMedicaService.obtenerTodo());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener Receta Medica por ID's", description = "Retorna las Receta Medica buscada por ID'S")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receta Medica encontrado"),
            @ApiResponse(responseCode = "404", description = "Receta Medica ingresada no encontrada!")
    })
    public ResponseEntity<RecetaMedicaResponseDTO> obtenerPorNumReceta(@PathVariable Long id){
        return recetaMedicaService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @Operation(summary = "Receta Medica",description = "Guarda la nueva Receta Medica ingresada")
    @ApiResponse(responseCode = "201", description = "Receta Medica creada exitosamente")
    public ResponseEntity<RecetaMedicaResponseDTO> guardarRecetaMedica(@Valid @RequestBody RecetaMedicaRequestDTO dto){
        RecetaMedicaResponseDTO response = recetaMedicaService.guardar(dto);
        return  ResponseEntity.status(201).body(response);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Receta Medica existente",description = "Actualiza y guarda la informacion cambiada en la Receta Medica por ID's")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receta Medica actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "La Receta Medica ingresada no existe")
    })
    public ResponseEntity<RecetaMedicaResponseDTO> actualizar (@PathVariable Long id,@Valid @RequestBody RecetaMedicaRequestDTO dto){
        return recetaMedicaService.actualizar(id,dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary ="Eliminar Receta Medica existente", description = "Elimina una Receta Medica buscada por ID's")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receta Medica eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "La Receta Medica ingresada no existe")
    })
        public ResponseEntity<Void>eliminar(@PathVariable Long id){
            if(recetaMedicaService.obtenerPorId(id).isEmpty()){
                return ResponseEntity.notFound().build();
            }
            recetaMedicaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
    }

