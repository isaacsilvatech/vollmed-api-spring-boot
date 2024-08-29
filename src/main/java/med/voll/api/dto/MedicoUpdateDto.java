package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoUpdateDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
