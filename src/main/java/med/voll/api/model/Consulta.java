package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.ConsultaDto;

import java.time.LocalDateTime;

@Table(name = "consulta")
@Entity(name = "Consulta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    private LocalDateTime data;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime data) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
    }
}
