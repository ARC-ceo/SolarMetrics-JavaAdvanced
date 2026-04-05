package com.oracle.solarmetrics.gateways.dtos.painelSolarDto;

import com.oracle.solarmetrics.domains.PainelSolar;
import com.oracle.solarmetrics.domains.Sistema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PainelSolarRequestUpdateDto {

    @NotBlank
    private String id;

    @NotBlank(message = "O modelo é obrigatório")
    @Size(min = 2, max = 50, message = "O modelo deve ter entre 2 e 50 caracteres")
    private String modelo;

    @NotBlank(message = "O fabricante é obrigatório")
    @Size(min = 2, max = 50, message = "O fabricante deve ter entre 2 e 50 caracteres")
    private String fabricante;

    @NotNull(message = "A potência máxima é obrigatória")
    @Min(value = 1, message = "A potência máxima deve ser maior que 0")
    private Integer potenciaMaxima;

    @NotNull(message = "A data de fabricação é obrigatória")
    @PastOrPresent(message = "A data de fabricação não pode ser no futuro")
    private LocalDate dataFabricacao;

    @NotNull(message = "A eficiência é obrigatória")
    @Min(value = 1, message = "A eficiência deve ser maior que 0%")
    @Max(value = 100, message = "A eficiência não pode ultrapassar 100%")
    private Integer eficiencia;

    @NotBlank(message = "O sistemaId é obrigatório")
    private String sistemaId;

    public PainelSolar toPainelSolar(){
        return PainelSolar.builder()
                .id(id)
                .modelo(modelo)
                .fabricante(fabricante)
                .potenciaMaxima(potenciaMaxima)
                .dataFabricacao(dataFabricacao)
                .eficiencia(eficiencia)
                .sistema(Sistema.builder()
                        .id(sistemaId)
                        .build())
                .build();
    }
}
