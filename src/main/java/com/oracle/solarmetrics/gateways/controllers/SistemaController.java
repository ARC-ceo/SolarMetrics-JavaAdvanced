package com.oracle.solarmetrics.gateways.controllers;
import com.oracle.solarmetrics.domains.Sistema;
import com.oracle.solarmetrics.gateways.dtos.sistemaDto.*;
import com.oracle.solarmetrics.services.SistemaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sistema")
@RequiredArgsConstructor
public class SistemaController implements SistemaControllerInterface {

    private final SistemaService sistemaService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<SistemaResponseDto> create(@RequestBody @Valid SistemaRequestDto sistemaRequestDto) {
        Sistema sistema = sistemaService.create(sistemaRequestDto.toSistema());
        return ResponseEntity.status(HttpStatus.CREATED).body(SistemaResponseDto.fromSistema(sistema));
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<SistemaResponseDto> update(@RequestBody @Valid SistemaRequestUpdateDto sistemaRequestUpdateDto) {
        Sistema sistema = sistemaService.update(sistemaRequestUpdateDto.toSistema());
        return ResponseEntity.ok().body(SistemaResponseDto.fromSistema(sistema));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<SistemaResponseIdDto> getId(@PathVariable("id") String id) {
        Sistema sistema = sistemaService.getId(id);
        return ResponseEntity.ok().body(SistemaResponseIdDto.fromSistema(sistema));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<SistemaResponseDto>> getAll() {
        List<Sistema> sistemas = sistemaService.getAll();
        if (sistemas.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity.ok(sistemas.stream()
                    .map(SistemaResponseDto::fromSistema)
                    .toList());
        }
    }

    @GetMapping(value = "/cliente/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<List<SistemaResponseIdDto>> getSistemasCliente(@PathVariable("id") String id) {
        List<Sistema> sistemas = sistemaService.getSistemasCliente(id);
        if (sistemas.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity.ok(sistemas.stream()
                    .map(SistemaResponseIdDto::fromSistema)
                    .toList());
        }
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<SistemaResponseDto> patch(@PathVariable("id") String id, @RequestBody @Valid SistemaRequestPatchDto sistemaRequestPatchDto) {
        Sistema sistema = sistemaService.patch(id, sistemaRequestPatchDto.toSistema());
        return ResponseEntity.ok().body(SistemaResponseDto.fromSistema(sistema));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        sistemaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
