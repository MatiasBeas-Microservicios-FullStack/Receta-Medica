package cl.koz.receta_medica.service;

import cl.koz.receta_medica.DTO.RecetaMedicaRequestDTO;
import cl.koz.receta_medica.DTO.RecetaMedicaResponseDTO;
import cl.koz.receta_medica.model.RecetaMedica;
import cl.koz.receta_medica.repository.RecetaMedicaRepository;
import cl.koz.receta_medica.webClient.HistorialClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecetaMedicaService {
    private final RecetaMedicaRepository recetaMedicaRepository;
    private final HistorialClient historialClient;

    private RecetaMedicaResponseDTO mapToDTO(RecetaMedica recetaMedica) {
        String diagnostico = historialClient.obtenerDiagnostico(
                recetaMedica.getNumFicha(),
                recetaMedica.getRunPaciente()
        );

        return new RecetaMedicaResponseDTO(
                recetaMedica.getNumReceta(),
                recetaMedica.getFechaEmision(),
                recetaMedica.getNumFicha(),
                recetaMedica.getRunPaciente(),
                diagnostico
        );
    }
    public List<RecetaMedicaResponseDTO> obtenerTodo(){
        return  recetaMedicaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public Optional<RecetaMedicaResponseDTO> obtenerPorId(Long id){
        return recetaMedicaRepository.findById(id).map(this::mapToDTO);
    }
    public RecetaMedicaResponseDTO guardar (RecetaMedicaRequestDTO dto){
        if (!historialClient.existeHistorial(dto.getNumFicha())) {
            throw new RuntimeException(
                    "El historial con número de ficha " + dto.getNumFicha() + " no existe"
            );
        }

        RecetaMedica recetaMedica = new RecetaMedica(
                null,
                dto.getFechaEmision(),
                dto.getNumFicha(),
                dto.getRunPaciente()
        );
        return mapToDTO(recetaMedicaRepository.save(recetaMedica));
    }
    public Optional<RecetaMedicaResponseDTO> actualizar (Long id, RecetaMedicaRequestDTO dto ){
        return  recetaMedicaRepository.findById(id).map(existente ->{
            existente.setFechaEmision(dto.getFechaEmision());
            existente.setRunPaciente(dto.getRunPaciente());
            return mapToDTO(recetaMedicaRepository.save(existente));
        });
    }
    public void eliminar (Long id){
        recetaMedicaRepository.deleteById(id);
    }
}
