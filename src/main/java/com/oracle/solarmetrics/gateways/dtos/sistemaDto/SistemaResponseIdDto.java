package com.oracle.solarmetrics.gateways.dtos.sistemaDto;

import com.oracle.solarmetrics.domains.Endereco;
import com.oracle.solarmetrics.domains.Sistema;
import com.oracle.solarmetrics.domains.StatusSistema;
import com.oracle.solarmetrics.gateways.dtos.EnderecoDto.EnderecoResponseDto;

import java.time.LocalDate;

public record SistemaResponseIdDto(
        String nomeInstalacao,
        LocalDate dataInstalacao,
        Integer potenciaTotal,
        StatusSistema status,
        String id,
        EnderecoResponseDto endereco
){
    public static SistemaResponseIdDto fromSistema(Sistema sistema){
        return new SistemaResponseIdDto (
                sistema.getNomeInstalacao(),
                sistema.getDataInstalacao(),
                sistema.getPotenciaTotal(),
                sistema.getStatus(),
                sistema.getId(),
                sistema.getEndereco() != null
                        ? EnderecoResponseDto.fromEndereco(
                        sistema.getEndereco()
                )
                        : null
        );
    }
}