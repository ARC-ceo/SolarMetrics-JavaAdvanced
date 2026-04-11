package com.oracle.solarmetrics.gateways.dtos.emailDto;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.domains.Usuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailQueueDto {
    private String name;
    private String email;

    public static EmailQueueDto fromUsuario(Cliente cliente) {
        return EmailQueueDto.builder()
                .name(cliente.getNome())
                .email(cliente.getEmail())
                .build();
    }
}