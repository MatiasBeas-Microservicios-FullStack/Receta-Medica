package cl.koz.receta_medica.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaMedicaResponseDTO {
    private Long numReceta;

    private Date fechaEmision;

    private Integer numFicha;
    private String runPaciente;
    private String diagnostico;
}
