package com.oracle.solarmetrics.domains;


import jakarta.persistence.*;
import lombok.*;

@With
@Getter
@Entity(name = "SM_ENDERECO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    @ManyToOne
    private Cidade cidade;
}
