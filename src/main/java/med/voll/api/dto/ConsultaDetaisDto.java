package med.voll.api.dto;

import java.time.LocalDateTime;

public record ConsultaDetaisDto(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
) {
}
