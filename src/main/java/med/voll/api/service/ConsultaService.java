package med.voll.api.service;

import med.voll.api.dto.ConsultaCancelarDto;
import med.voll.api.dto.ConsultaDto;
import med.voll.api.exception.ValidationException;
import med.voll.api.model.Consulta;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ConsultaService {

    @Autowired
    List<Validator<ConsultaDto>> consultaDtoValidators;

    @Autowired
    List<Validator<ConsultaCancelarDto>> consultaCancelarDtoValidators;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public Consulta agendar(ConsultaDto consultaDto) {
        if (!pacienteRepository.existsById(consultaDto.idPaciente())) {
            throw new ValidationException("Id do paciente informado não existe!");
        }
        if (!medicoRepository.existsById(consultaDto.idMedico())) {
            throw new ValidationException("Id do médico informado não existe!");
        }

        consultaDtoValidators.forEach(v -> v.validate(consultaDto));

        var paciente = pacienteRepository.getReferenceById(consultaDto.idPaciente());
        var medico = getMedico(consultaDto.idMedico(), consultaDto.especialidade(), consultaDto.data());
        var consulta = new Consulta(paciente, medico, consultaDto.data());

        return consultaRepository.save(consulta);
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

    public void cancelar(ConsultaCancelarDto consultaCancelarDto) {
        var consulta = consultaRepository.findById(consultaCancelarDto.id()).orElseThrow(() -> new ValidationException("Consulta não encontrada!"));
        consultaCancelarDtoValidators.forEach(v -> v.validate(consultaCancelarDto));
        consulta.cancelar(consultaCancelarDto.motivoCancelamento());
    }
}
