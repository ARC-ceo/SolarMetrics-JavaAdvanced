package com.oracle.solarmetrics.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@With
@Getter
@Entity(name = "SM_SISTEMA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nomeInstalacao;
    private LocalDate dataInstalacao;
    private int potenciaTotal;
    private String status;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(
            mappedBy = "sistema",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<PainelSolar> painelSolar;

    @OneToMany(
            mappedBy = "sistema",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Sensor> sensor;
}
