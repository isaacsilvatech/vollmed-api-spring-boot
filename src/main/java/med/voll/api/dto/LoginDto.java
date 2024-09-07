package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull
        String login,
        @NotNull
        String senha) {
}
