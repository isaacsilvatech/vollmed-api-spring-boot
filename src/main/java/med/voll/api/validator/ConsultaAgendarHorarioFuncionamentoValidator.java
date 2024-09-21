package med.voll.api.validator;

import med.voll.api.dto.ConsultaDto;
import med.voll.api.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ConsultaAgendarHorarioFuncionamentoValidator implements Validator<ConsultaDto> {

    @Override
    public void validate(ConsultaDto consultaDto) throws ValidationException {
        var data = consultaDto.data();
        var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesHorarioFuncionamento = data.getHour() < 7;
        var depoisHorarioFuncionamento = data.getHour() > 18;
        var depoisMinutosHorarioFuncionamento = (data.getHour() == 18 && data.getMinute() > 0);

        if (domingo || antesHorarioFuncionamento || depoisHorarioFuncionamento || depoisMinutosHorarioFuncionamento) {
            throw new ValidationException("A consulta deve ser agendada dentro do hórario de funcionamento!");
        }
    }
}
