package com.oracle.solarmetrics.gateways.dtos.clientsDto;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String estado,
        Boolean erro
) {
}
