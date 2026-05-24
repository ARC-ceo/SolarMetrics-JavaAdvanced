package com.oracle.solarmetrics.gateways.dtos.clienteDto;


import com.oracle.solarmetrics.domains.Cliente;

public record ClienteResponseDto (
        String nome,
        String tipoUser,
        String id,
        String telefone,
        String email
){
    public static ClienteResponseDto fromCliente(Cliente cliente){
        return new ClienteResponseDto (
                cliente.getNome(),
                cliente.getTipoUser(),
                cliente.getId(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }
}