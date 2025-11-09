package com.oracle.solarmetrics.gateways;
import com.oracle.solarmetrics.domains.Sistema;
import com.oracle.solarmetrics.gateways.dtos.SistemaRequestDto;
import com.oracle.solarmetrics.gateways.dtos.SistemaRequestPatchDto;
import com.oracle.solarmetrics.gateways.dtos.SistemaRequestUpdateDto;
import com.oracle.solarmetrics.gateways.dtos.response.SistemaResponseDto;
import com.oracle.solarmetrics.services.SistemaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sistema")
@RequiredArgsConstructor
public class SistemaController implements SistemaControllerInterface {

    private final SistemaService sistemaService;

    @PostMapping
    public ResponseEntity<SistemaResponseDto> create(@RequestBody @Valid SistemaRequestDto sistemaRequestDto) {
        Sistema sistema = sistemaService.create(sistemaRequestDto.toSistema());
        return ResponseEntity.status(HttpStatus.CREATED).body(SistemaResponseDto.fromSistema(sistema));
    }

    @PutMapping()
    public ResponseEntity<SistemaResponseDto> update(@RequestBody @Valid SistemaRequestUpdateDto sistemaRequestUpdateDto) {
        Sistema sistema = sistemaService.update(sistemaRequestUpdateDto.toSistema());
        return ResponseEntity.ok().body(SistemaResponseDto.fromSistema(sistema));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SistemaResponseDto> getId(@PathVariable("id") String id) {
        Sistema sistema = sistemaService.getId(id);
        return ResponseEntity.ok().body(SistemaResponseDto.fromSistema(sistema));
    }

    @GetMapping
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
    public ResponseEntity<List<SistemaResponseDto>> getSistemasCliente(@PathVariable("id") String id) {
        List<Sistema> sistemas = sistemaService.getSistemasCliente(id);
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

    @PatchMapping(value = "/{id}")
    public ResponseEntity<SistemaResponseDto> patch(@PathVariable("id") String id, @RequestBody @Valid SistemaRequestPatchDto sistemaRequestPatchDto) {
        Sistema sistema = sistemaService.patch(id, sistemaRequestPatchDto.toSistema());
        return ResponseEntity.ok().body(SistemaResponseDto.fromSistema(sistema));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        sistemaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
