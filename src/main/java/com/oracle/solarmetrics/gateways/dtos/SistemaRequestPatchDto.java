package com.oracle.solarmetrics.gateways.dtos;

import com.oracle.solarmetrics.domains.Sistema;
import com.oracle.solarmetrics.domains.StatusSistema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SistemaRequestPatchDto {

    private String nomeInstalacao;

    @PastOrPresent(message = "A data de instalação não pode ser no futuro")
    private LocalDate dataInstalacao;

    @Min(value = 1, message = "O valor deve ser maior que 0")
    private Integer potenciaTotal;

    private StatusSistema status;

    public Sistema toSistema(){
        return Sistema.builder()
                .nomeInstalacao(nomeInstalacao)
                .dataInstalacao(dataInstalacao)
                .potenciaTotal(potenciaTotal)
                .status(status)
                .build();
    }

}
