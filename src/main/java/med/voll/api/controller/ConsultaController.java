package med.voll.api.controller;

import med.voll.api.dto.ConsultaDetaisDto;
import med.voll.api.dto.ConsultaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @PostMapping
    public ResponseEntity<ConsultaDetaisDto> agendar(@RequestBody ConsultaDto consultaDto) {

        System.out.println(consultaDto.data());
        return ResponseEntity.ok(new ConsultaDetaisDto(null, null, null, null));
    }

}
