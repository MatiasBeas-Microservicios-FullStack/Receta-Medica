package cl.koz.receta_medica.repository;

import cl.koz.receta_medica.model.RecetaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaMedicaRepository extends JpaRepository<RecetaMedica, Long> {
}
