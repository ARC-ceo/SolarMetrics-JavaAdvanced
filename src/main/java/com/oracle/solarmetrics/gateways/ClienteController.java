package com.oracle.solarmetrics.gateways;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.gateways.dtos.ClienteRequestDto;
import com.oracle.solarmetrics.gateways.dtos.ClienteRequestPatchDto;
import com.oracle.solarmetrics.gateways.dtos.ClienteRequestUpdateDto;
import com.oracle.solarmetrics.gateways.dtos.response.ClienteResponseDto;
import com.oracle.solarmetrics.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ClienteResponseDto> update(@RequestBody @Valid ClienteRequestUpdateDto clienteRequestUpdateDto) {
        Cliente cliente = clienteService.update(clienteRequestUpdateDto.toCliente());
        return ResponseEntity.ok().body(ClienteResponseDto.fromCliente(cliente));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteResponseDto> getId(@PathVariable("id") String id) {
        Cliente cliente = clienteService.getId(id);
        return ResponseEntity.ok().body(ClienteResponseDto.fromCliente(cliente));
    }

    @GetMapping
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
    public ResponseEntity<ClienteResponseDto> patch(@PathVariable("id") String id, @RequestBody @Valid ClienteRequestPatchDto clienteRequestPatchDto) {
        Cliente cliente = clienteService.patch(id, clienteRequestPatchDto.toCliente());
        return ResponseEntity.ok().body(ClienteResponseDto.fromCliente(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
