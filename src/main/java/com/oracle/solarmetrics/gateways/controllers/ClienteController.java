package com.oracle.solarmetrics.gateways.controllers;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.gateways.dtos.clienteDto.ClienteRequestDto;
import com.oracle.solarmetrics.gateways.dtos.clienteDto.ClienteRequestPatchDto;
import com.oracle.solarmetrics.gateways.dtos.clienteDto.ClienteRequestUpdateDto;
import com.oracle.solarmetrics.gateways.dtos.clienteDto.ClienteResponseDto;
import com.oracle.solarmetrics.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController implements ClienteControllerInterface {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteRequestDto clienteRequestDto) {
        Cliente cliente = clienteService.create(clienteRequestDto.toCliente());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDto.fromCliente(cliente));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<ClienteResponseDto> update(@RequestBody @Valid ClienteRequestUpdateDto clienteRequestUpdateDto) {
        Cliente cliente = clienteService.update(clienteRequestUpdateDto.toCliente());
        return ResponseEntity.ok().body(ClienteResponseDto.fromCliente(cliente));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<ClienteResponseDto> getId(@PathVariable("id") String id) {
        Cliente cliente = clienteService.getId(id);
        return ResponseEntity.ok().body(ClienteResponseDto.fromCliente(cliente));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<ClienteResponseDto>> getAll() {
        List<Cliente> clientes = clienteService.getAll();
        if (clientes.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity.ok(clientes.stream()
                    .map(ClienteResponseDto::fromCliente)
                    .toList());
        }
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<ClienteResponseDto> patch(@PathVariable("id") String id, @RequestBody @Valid ClienteRequestPatchDto clienteRequestPatchDto) {
        Cliente cliente = clienteService.patch(id, clienteRequestPatchDto.toCliente());
        return ResponseEntity.ok().body(ClienteResponseDto.fromCliente(cliente));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
