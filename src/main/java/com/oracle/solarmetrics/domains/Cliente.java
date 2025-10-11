package com.oracle.solarmetrics.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@With
@Getter
@Entity(name = "SM_USUARIO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String nome;
    private String telefone;
    private String tipoUser;

    @OneToMany(
            mappedBy = "cliente",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Sistema> sistemas;
}
