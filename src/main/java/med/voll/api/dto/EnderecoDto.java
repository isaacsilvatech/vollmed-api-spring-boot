package med.voll.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Endereco;

public record EnderecoDto(@NotBlank String logradouro,

                          @NotBlank String bairro,

                          @NotBlank @Pattern(regexp = "\\d{8}") String cep,

                          @NotBlank String cidade,

                          @NotBlank String uf,

                          String complemento, String numero) {

    public EnderecoDto(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(), endereco.getComplemento(), endereco.getNumero());
    }
}
