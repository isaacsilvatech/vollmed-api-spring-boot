package med.voll.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.MedicoDto;
import med.voll.api.dto.MedicoUpdateDto;

import java.util.Objects;

@Table(name = "medico")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoDto medicoDto) {
        this.nome = medicoDto.nome();
        this.email = medicoDto.email();
        this.crm = medicoDto.crm();
        this.telefone = medicoDto.telefone();
        this.especialidade = medicoDto.especialidade();
        this.endereco = new Endereco(medicoDto.endereco());
        this.ativo = true;
    }

    public void update(MedicoUpdateDto medicoUpdateDto) {
        if (Objects.nonNull(medicoUpdateDto.nome())) {
            this.nome = medicoUpdateDto.nome();
        }
        if (Objects.nonNull(medicoUpdateDto.telefone())) {
            this.telefone = medicoUpdateDto.telefone();
        }
        if (Objects.nonNull(medicoUpdateDto.endereco())) {
            this.endereco.update(medicoUpdateDto.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
