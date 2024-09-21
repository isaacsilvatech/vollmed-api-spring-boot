package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.MotivoCancelamento;

public record ConsultaCancelarDto(
        @NotNull
        Long id,

        @NotNull
        MotivoCancelamento motivoCancelamento) {
}
