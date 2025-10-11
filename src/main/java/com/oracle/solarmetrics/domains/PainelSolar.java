package com.oracle.solarmetrics.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@With
@Getter
@Entity(name = "SM_PAINEL_SOLAR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class PainelSolar {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String modelo;
    private String fabricante;
    private int potenciaMaxima;
    private LocalDate dataFabricacao;
    private int eficiencia;

    @ManyToOne
    private Sistema sistema;
}
