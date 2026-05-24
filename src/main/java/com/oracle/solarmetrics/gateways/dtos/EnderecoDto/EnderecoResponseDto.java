package com.oracle.solarmetrics.gateways.dtos.EnderecoDto;

import com.oracle.solarmetrics.domains.Endereco;

public record EnderecoResponseDto(

        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        String cidade,
        String uf

) {

    public static EnderecoResponseDto fromEndereco(
            Endereco endereco
    ) {

        return new EnderecoResponseDto(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade().getNome(),
                endereco.getCidade().getEstado().getUf()
        );
    }
}
