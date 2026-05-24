package com.oracle.solarmetrics.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@With
@Getter
@Entity(name = "SM_ESTADO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    @Column(unique = true, length = 2)
    private String uf;

    @OneToMany(
            mappedBy = "estado",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Cidade> cidades;
}