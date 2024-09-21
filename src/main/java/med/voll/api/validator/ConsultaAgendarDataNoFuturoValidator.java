package med.voll.api.validator;

import med.voll.api.dto.ConsultaDto;
import med.voll.api.exception.ValidationException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsultaAgendarDataNoFuturoValidator implements Validator<ConsultaDto> {

    @Override
    public void validate(ConsultaDto consultaDto) throws ValidationException {
        var hoje = LocalDateTime.now();
        var data = consultaDto.data();
        if(data.isBefore(hoje)) {
            throw new ValidationException("Não é possivel agendar para uma data no passado!");
        }
    }
}
