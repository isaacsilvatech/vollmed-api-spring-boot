package med.voll.api.dto;

import med.voll.api.model.Consulta;

import java.time.LocalDateTime;

public record ConsultaDetaisDto(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
) {
    public ConsultaDetaisDto(Consulta consulta) {
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getData());
    }
}
