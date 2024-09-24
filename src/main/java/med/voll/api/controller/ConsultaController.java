package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.dto.ConsultaCancelarDto;
import med.voll.api.dto.ConsultaDetaisDto;
import med.voll.api.dto.ConsultaDto;
import med.voll.api.model.Consulta;
import med.voll.api.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consulta")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDetaisDto> agendar(@RequestBody ConsultaDto consultaDto, UriComponentsBuilder uriComponentsBuilder) {
        var consulta = consultaService.agendar(consultaDto);
        var uri = uriComponentsBuilder.path("/consulta/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ConsultaDetaisDto(consulta));
    }

    @PostMapping
    @RequestMapping("/cancelar")
    public ResponseEntity<ConsultaDetaisDto> cancelar(@RequestBody ConsultaCancelarDto consultaCancelarDto) {
        var consulta = consultaService.cancelar(consultaCancelarDto);
        return ResponseEntity.ok(new ConsultaDetaisDto(consulta));
    }
}
