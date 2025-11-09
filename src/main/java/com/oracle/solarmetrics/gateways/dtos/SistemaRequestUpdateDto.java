package com.oracle.solarmetrics.gateways.dtos;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.domains.Sistema;
import com.oracle.solarmetrics.domains.StatusSistema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SistemaRequestUpdateDto {

    @NotBlank
    private String id;

    @NotBlank
    private String nomeInstalacao;

    @NotNull(message = "A data de instalação é obrigatória")
    @PastOrPresent(message = "A data de instalação não pode ser no futuro")
    private LocalDate dataInstalacao;

    @NotNull
    @Min(value = 1, message = "O valor deve ser maior que 0")
    private Integer potenciaTotal;

    @NotNull(message = "O status é obrigatório")
    private StatusSistema status;

    @NotBlank(message = "O id do cliente é obrigatório")
    private String clienteId;

    public Sistema toSistema(){
        return Sistema.builder()
                .id(id)
                .nomeInstalacao(nomeInstalacao)
                .dataInstalacao(dataInstalacao)
                .potenciaTotal(potenciaTotal)
                .status(status)
                .cliente(Cliente.builder()
                        .id(clienteId)
                        .build())
                .build();
    }
}
