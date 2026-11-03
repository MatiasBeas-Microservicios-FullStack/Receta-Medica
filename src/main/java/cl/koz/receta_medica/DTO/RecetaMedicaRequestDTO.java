package cl.koz.receta_medica.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RecetaMedicaRequestDTO {
    private Long numReceta;

    @NotNull(message = "La fecha de emision no puede estar VACIA!!")
    private Date fechaEmision;

    @NotNull(message = "El numero de ficha no puede estar VACIA!!")
    private Integer numFicha;
    @NotBlank(message = "el rut del paciente es OBLIGATORIO")
    private String runPaciente;
}
