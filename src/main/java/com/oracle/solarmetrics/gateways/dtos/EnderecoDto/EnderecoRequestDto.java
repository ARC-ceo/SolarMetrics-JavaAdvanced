package com.oracle.solarmetrics.gateways.dtos.EnderecoDto;

import com.oracle.solarmetrics.domains.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoRequestDto {

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(
            regexp = "^\\d{5}-?\\d{3}$",
            message = "O CEP deve estar no formato 00000-000"
    )
    private String cep;

    @NotBlank(message = "O número é obrigatório")
    @Size(
            max = 10,
            message = "O número deve ter no máximo 10 caracteres"
    )
    private String numero;

    @Size(
            max = 100,
            message = "O complemento deve ter no máximo 100 caracteres"
    )
    private String complemento;

    public Endereco toEndereco() {

        return Endereco.builder()
                .cep(cep)
                .numero(numero)
                .complemento(complemento)
                .build();
    }
}