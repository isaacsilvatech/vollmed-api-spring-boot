package med.voll.api.dto;

import med.voll.api.model.Paciente;

public record PacienteDetailsDto(Long id, String nome, String email, String cpf, String telefone,
                                 EnderecoDto endereco) {

    public PacienteDetailsDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(),
                new EnderecoDto(paciente.getEndereco()));
    }
}
