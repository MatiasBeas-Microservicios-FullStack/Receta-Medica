package cl.koz.receta_medica.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}