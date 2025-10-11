package com.oracle.solarmetrics.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@With
@Getter
@Entity(name = "SM_SENSOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String tipo;
    private String status;
    private String localizacao;

    @ManyToOne
    private Sistema sistema;

    @OneToMany(
            mappedBy = "sensor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Monitoramento> monitoramento;
}
