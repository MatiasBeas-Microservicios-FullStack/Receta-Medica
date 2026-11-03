package cl.koz.receta_medica.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Receta Medica")
public class RecetaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 3)
    private Long numReceta;

    @Column(nullable = false)
    private Date fechaEmision;

    @Column(nullable = false)
    private Integer numFicha;

    @Column(nullable = false,length = 20)
    private String runPaciente;
}
