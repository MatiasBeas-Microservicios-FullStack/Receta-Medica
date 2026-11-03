package cl.koz.receta_medica.webClient;

import cl.koz.receta_medica.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HistorialClient {
    private final WebClient webClient;

    public String obtenerDiagnostico(Integer historialNumFicha, String pacienteRun){
        try {
            Map response = webClient
                    .get()
                    .uri("/historialMedico/" + historialNumFicha + "/" + pacienteRun)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return response != null ? (String) response.get("diagnostico") : "Sin Historial";
        } catch (Exception e) {
            return "Sin Historial";
        }
    }
    public boolean existeHistorial(Integer numFicha) {
        try {
            Map response = webClient
                    .get()
                    .uri("/historialMedico/" + numFicha)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return response != null;
        } catch (Exception e) {
            return false;
        }
    }

}
