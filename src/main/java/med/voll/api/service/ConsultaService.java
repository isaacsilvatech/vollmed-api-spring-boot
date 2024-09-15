package med.voll.api.service;

import med.voll.api.exception.ValidationException;
import med.voll.api.model.Consulta;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public Consulta agendar(Long idPaciente, Long idMedico, Especialidade especialidade, LocalDateTime data) {
        if (!pacienteRepository.existsById(idPaciente)) {
            throw new ValidationException("Id do paciente informado não existe!");
        }
        if (!medicoRepository.existsById(idMedico)) {
            throw new ValidationException("Id do médico informado não existe!");
        }

        var paciente = pacienteRepository.getReferenceById(idPaciente);
        var medico = getMedico(idMedico, especialidade, data);

        return null;
    }

    private Medico getMedico(Long idMedico, Especialidade especialidade, LocalDateTime data) {
        if (Objects.nonNull(idMedico)) {
            return medicoRepository.getReferenceById(idMedico);
        }
        if (Objects.isNull(especialidade)) {
            throw new ValidationException("Especialidade é obrigatória quando médico não for escolhido!");
        }
        return medicoRepository.findFirstRandomByEspecialidadeAndFreeData(especialidade, data);
    }
}
