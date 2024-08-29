package med.voll.api.dto;

import med.voll.api.model.Paciente;

public record PacienteListDto(Long id, String nome, String email, String cpf) {

    public PacienteListDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
