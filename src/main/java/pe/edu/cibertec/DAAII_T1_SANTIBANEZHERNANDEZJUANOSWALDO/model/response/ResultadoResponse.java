package pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoResponse {
    private Boolean respuesta;
    private String mensaje;
}