package med.voll.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.EnderecoDto;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDto endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }

    public void update(EnderecoDto endereco) {
        if (Objects.nonNull(endereco.logradouro())) {
            this.logradouro = endereco.logradouro();
        }
        if (Objects.nonNull(endereco.bairro())) {
            this.bairro = endereco.bairro();
        }
        if (Objects.nonNull(endereco.cep())) {
            this.cep = endereco.cep();
        }
        if (Objects.nonNull(endereco.cidade())) {
            this.cidade = endereco.cidade();
        }
        if (Objects.nonNull(endereco.uf())) {
            this.uf = endereco.uf();
        }
        if (Objects.nonNull(endereco.numero())) {
            this.numero = endereco.numero();
        }
        if (Objects.nonNull(endereco.complemento())) {
            this.complemento = endereco.complemento();
        }
    }
}
