package com.oracle.solarmetrics.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@With
@Getter
@Entity(name = "SM_CIDADE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String nome;

    @ManyToOne
    private Estado estado;

    @OneToMany(
            mappedBy = "cidade",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Endereco> enderecos;
}
