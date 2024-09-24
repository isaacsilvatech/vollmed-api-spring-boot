package med.voll.api.dto;

import med.voll.api.model.Consulta;
import med.voll.api.model.MotivoCancelamento;

import java.time.LocalDateTime;

public record ConsultaDetaisDto(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        MotivoCancelamento motivoCancelamento
) {
    public ConsultaDetaisDto(Consulta consulta) {
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getData(),
                consulta.getMotivoCancelamento());
    }
}
