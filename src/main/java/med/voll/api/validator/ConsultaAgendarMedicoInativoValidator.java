package med.voll.api.validator;

import med.voll.api.dto.ConsultaDto;
import med.voll.api.exception.ValidationException;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ConsultaAgendarMedicoInativoValidator implements ConsultaAgendarValidator {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validate(ConsultaDto consultaDto) throws ValidationException {
        if (Objects.isNull(consultaDto.idMedico())) {
            return;
        }
        if (!medicoRepository.findAtivoById(consultaDto.idMedico())) {
            throw new ValidationException("Não é possivel agendar consultas com um médico inátivo!");
        }
    }
}
