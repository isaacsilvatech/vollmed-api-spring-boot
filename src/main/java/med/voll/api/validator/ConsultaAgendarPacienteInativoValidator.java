package med.voll.api.validator;

import med.voll.api.dto.ConsultaDto;
import med.voll.api.exception.ValidationException;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class ConsultaAgendarPacienteInativoValidator implements ConsultaAgendarValidator {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validate(ConsultaDto consultaDto) throws ValidationException {
        if(!pacienteRepository.findAtivoById(consultaDto.idPaciente())) {
            throw new ValidationException("Não é possivel agendar consultas com um paciente inativo!");
        }
    }
}
