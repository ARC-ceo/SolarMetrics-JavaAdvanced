package com.oracle.solarmetrics.gateways.dtos.response;


import com.oracle.solarmetrics.domains.Cliente;

public record ClienteResponseDto (
        String nome,
        String tipoUser
){
    public static ClienteResponseDto fromCliente(Cliente cliente){
        return new ClienteResponseDto (
                cliente.getNome(),
                cliente.getTipoUser()
        );
    }
}