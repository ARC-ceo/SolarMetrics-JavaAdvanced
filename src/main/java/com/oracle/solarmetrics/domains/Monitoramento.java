package com.oracle.solarmetrics.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@With
@Getter
@Entity(name = "SM_MONITORAMENTO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Monitoramento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDate periodo;
    private int valorLido;
    private int mediaLeitura;
    private int maximaLeitura;

    @ManyToOne
    private Sensor sensor;
}
