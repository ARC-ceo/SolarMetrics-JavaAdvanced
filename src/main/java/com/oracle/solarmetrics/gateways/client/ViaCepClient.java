package com.oracle.solarmetrics.gateways.client;

import com.oracle.solarmetrics.gateways.dtos.clientsDto.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "viacep",
        url = "https://viacep.com.br/ws"
)
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    ViaCepResponse buscarCep(
            @PathVariable("cep") String cep
    );
}
