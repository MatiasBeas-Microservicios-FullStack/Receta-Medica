package cl.koz.receta_medica.config;

import cl.koz.receta_medica.model.RecetaMedica;
import cl.koz.receta_medica.model.Role;
import cl.koz.receta_medica.model.User;
import cl.koz.receta_medica.repository.RecetaMedicaRepository;
import cl.koz.receta_medica.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RecetaMedicaRepository recetaMedicaRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("Maty").isEmpty()) {
            User user = new User();
            user.setUsername("Maty");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            log.info(">>> DataInitializer: Usuario Maty creado correctamente.");
        }
        if (recetaMedicaRepository.count() > 0) {
            log.info(">>> DataInitializer: la BD ya tiene datos, se omite la carga inicial.");
            return;
        }
        log.info(">>> DataInitializer: BD vacía detectada, insertando datos de prueba...");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        recetaMedicaRepository.save(new RecetaMedica(null, sdf.parse("2025-01-10"), 1, "22.359.190-6"));
        recetaMedicaRepository.save(new RecetaMedica(null, sdf.parse("2025-02-05"), 2, "18.765.432-1"));
        recetaMedicaRepository.save(new RecetaMedica(null, sdf.parse("2025-03-12"), 3, "11.111.111-1"));
        recetaMedicaRepository.save(new RecetaMedica(null, sdf.parse("2025-04-20"), 4, "22.222.222-2"));
        recetaMedicaRepository.save(new RecetaMedica(null, sdf.parse("2025-05-01"), 5, "10.333.333-3"));
        log.info(">>> DataInitializer: {} hospitalizaciones insertadas.", recetaMedicaRepository.count());
    }
}
