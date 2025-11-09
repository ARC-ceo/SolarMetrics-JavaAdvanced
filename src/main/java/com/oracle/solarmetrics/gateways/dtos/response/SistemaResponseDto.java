package com.oracle.solarmetrics.gateways.dtos.response;
import com.oracle.solarmetrics.domains.Sistema;
import com.oracle.solarmetrics.domains.StatusSistema;


import java.time.LocalDate;

public record SistemaResponseDto (
        String nomeInstalacao,
        LocalDate dataInstalacao,
        Integer potenciaTotal,
        StatusSistema status
){
    public static SistemaResponseDto fromSistema(Sistema sistema){
        return new SistemaResponseDto (
                sistema.getNomeInstalacao(),
                sistema.getDataInstalacao(),
                sistema.getPotenciaTotal(),
                sistema.getStatus()
        );
    }
}