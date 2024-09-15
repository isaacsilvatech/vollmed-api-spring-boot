package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.PacienteDetailsDto;
import med.voll.api.dto.PacienteDto;
import med.voll.api.dto.PacienteListDto;
import med.voll.api.dto.PacienteUpdateDto;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDetailsDto> create(@RequestBody @Valid PacienteDto pacienteDto, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = pacienteRepository.save(new Paciente(pacienteDto));
        var uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDetailsDto(paciente));
    }

    @GetMapping
    public ResponseEntity<PagedModel<PacienteListDto>> getList(Pageable pageable) {
        var pagedModel = new PagedModel<>(pacienteRepository.findAllByAtivoTrue(pageable).map(PacienteListDto::new));
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDetailsDto> getById(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteDetailsDto(paciente));
    }

    @PutMapping
    public ResponseEntity<PacienteDetailsDto> update(@RequestBody @Valid PacienteUpdateDto pacienteUpdateDto) {
        var paciente = pacienteRepository.getReferenceById(pacienteUpdateDto.id());
        paciente.update(pacienteUpdateDto);
        return ResponseEntity.ok(new PacienteDetailsDto(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
        return ResponseEntity.noContent().build();
    }
}
