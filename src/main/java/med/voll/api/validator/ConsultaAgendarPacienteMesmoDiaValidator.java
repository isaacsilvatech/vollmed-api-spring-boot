package med.voll.api.validator;

import med.voll.api.dto.ConsultaDto;
import med.voll.api.exception.ValidationException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ConsultaAgendarPacienteMesmoDiaValidator implements ConsultaAgendarValidator {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validate(ConsultaDto consultaDto) throws ValidationException {
        var primeiroHorario = consultaDto.data().withHour(7);
        var ultimoHorario = consultaDto.data().withHour(18);
        if(consultaRepository.existsByPacienteIdAndDataBetween(consultaDto.idPaciente(), primeiroHorario, ultimoHorario)) {
            throw new ValidationException("Não é possivel agendar mais de uma consulta por paciente!");
        }
    }
}
