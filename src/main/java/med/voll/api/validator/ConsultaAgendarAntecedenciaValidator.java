package med.voll.api.validator;

import med.voll.api.dto.ConsultaDto;
import med.voll.api.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ConsultaAgendarAntecedenciaValidator implements Validator<ConsultaDto> {

    @Override
    public void validate(ConsultaDto consultaDto) throws ValidationException {
        var agora = LocalDateTime.now();
        var data = consultaDto.data();
        var diferenca = Duration.between(agora, data).toMinutes();
        if (diferenca > 0 && diferenca < 30) {
            throw new ValidationException("A consulta deve ser agendada com antecedência minima de 30 minutos!");
        }
    }
}
