package com.oracle.solarmetrics.gateways.dtos.painelSolarDto;

import com.oracle.solarmetrics.domains.PainelSolar;

public record PainelSolarResponseDto(
        String id,
        String modelo,
        String fabricante,
        Integer potenciaMaxima
) {

    public static PainelSolarResponseDto fromPainelSolar(PainelSolar painelSolar){
        return new PainelSolarResponseDto (
                painelSolar.getId(),
                painelSolar.getModelo(),
                painelSolar.getFabricante(),
                painelSolar.getPotenciaMaxima()
        );
    }
}
