package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.PacienteDto;
import med.voll.api.dto.PacienteUpdateDto;

import java.util.Objects;


@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(PacienteDto pacienteDto) {
        this.nome = pacienteDto.nome();
        this.cpf = pacienteDto.cpf();
        this.email = pacienteDto.email();
        this.telefone = pacienteDto.telefone();
        this.endereco = new Endereco(pacienteDto.endereco());
        this.ativo = true;
    }

    public void update(PacienteUpdateDto pacienteUpdateDto) {
        if (Objects.nonNull(pacienteUpdateDto.nome())) {
            this.nome = pacienteUpdateDto.nome();
        }
        if (Objects.nonNull(pacienteUpdateDto.telefone())) {
            this.telefone = pacienteUpdateDto.telefone();
        }
        if (Objects.nonNull(pacienteUpdateDto.endereco())) {
            this.endereco.update(pacienteUpdateDto.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
