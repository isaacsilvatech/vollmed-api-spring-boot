package med.voll.api.dto;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public record MedicoDetailsDto(Long id, String name, String email, String crm, String telefone,
                               Especialidade especialidade, EnderecoDto endereco) {

    public MedicoDetailsDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
                medico.getEspecialidade(), new EnderecoDto(medico.getEndereco()));
    }
}
