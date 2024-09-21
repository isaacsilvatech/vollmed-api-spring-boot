package med.voll.api.validator;

import med.voll.api.dto.ConsultaDto;
import med.voll.api.exception.ValidationException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaAgendarMedicoComConsultaNaDataValidator implements Validator<ConsultaDto> {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validate(ConsultaDto consultaDto) throws ValidationException {
        if(consultaRepository.existsByMedicoIdAndData(consultaDto.idPaciente(), consultaDto.data())) {
            throw new ValidationException("Não é possivel agendar uma consulta para um médico com uma consulta nesse mesmo hórario!");
        }
    }
}
