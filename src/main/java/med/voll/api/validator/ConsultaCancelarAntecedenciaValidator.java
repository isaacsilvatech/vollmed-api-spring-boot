package med.voll.api.validator;

import med.voll.api.dto.ConsultaCancelarDto;
import med.voll.api.exception.ValidationException;
import med.voll.api.model.Consulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ConsultaCancelarAntecedenciaValidator implements ConsultaCancelarValidator {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validate(ConsultaCancelarDto consultaCancelarDto) throws ValidationException {
        Consulta consulta = consultaRepository.getReferenceById(consultaCancelarDto.id());
        var agora = LocalDateTime.now();
        var data = consulta.getData();
        var diferenca = Duration.between(agora, data).toHours();
        if (diferenca > 0 && diferenca < 24) {
            throw new ValidationException("A consulta só pode ser cancelada com antecedência minima de 24 horas!");
        }
    }
}
