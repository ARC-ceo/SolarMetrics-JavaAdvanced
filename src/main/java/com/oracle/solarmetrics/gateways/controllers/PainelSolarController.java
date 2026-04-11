package com.oracle.solarmetrics.gateways.controllers;

import com.oracle.solarmetrics.domains.PainelSolar;
import com.oracle.solarmetrics.domains.Sensor;
import com.oracle.solarmetrics.gateways.dtos.painelSolarDto.PainelSolarRequestDto;
import com.oracle.solarmetrics.gateways.dtos.painelSolarDto.PainelSolarRequestUpdateDto;
import com.oracle.solarmetrics.gateways.dtos.painelSolarDto.PainelSolarResponseDto;
import com.oracle.solarmetrics.gateways.dtos.sensorDto.SensorRequestDto;
import com.oracle.solarmetrics.gateways.dtos.sensorDto.SensorResponseDto;
import com.oracle.solarmetrics.services.PainelSolarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/painelsolar")
@RequiredArgsConstructor
public class PainelSolarController {

    private final PainelSolarService painelSolarService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<PainelSolarResponseDto> create(@RequestBody @Valid PainelSolarRequestDto painelSolarRequestDto) {
        PainelSolar painelSolar = painelSolarService.create(painelSolarRequestDto.toPainelSolar());
        return ResponseEntity.status(HttpStatus.CREATED).body(PainelSolarResponseDto.fromPainelSolar(painelSolar));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<PainelSolarResponseDto> update(@RequestBody @Valid PainelSolarRequestUpdateDto painelSolarRequestUpdateDto) {
        PainelSolar painelSolar = painelSolarService.update(painelSolarRequestUpdateDto.toPainelSolar());
        return ResponseEntity.ok().body(PainelSolarResponseDto.fromPainelSolar(painelSolar));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<PainelSolarResponseDto> getId(@PathVariable("id") String id) {
        PainelSolar painelSolar = painelSolarService.getId(id);
        return ResponseEntity.ok().body(PainelSolarResponseDto.fromPainelSolar(painelSolar));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<PainelSolarResponseDto>> getAll() {
        List<PainelSolar> painelSolars = painelSolarService.getAll();
        if (painelSolars.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity.ok(painelSolars.stream()
                    .map(PainelSolarResponseDto::fromPainelSolar)
                    .toList());
        }
    }

    @GetMapping(value = "/sistema/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<List<PainelSolarResponseDto>> getPainelSolarSistema(@PathVariable("id") String id) {
        List<PainelSolar> painelSolars = painelSolarService.getPainelSolarSistema(id);
        if (painelSolars.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity.ok(painelSolars.stream()
                    .map(PainelSolarResponseDto::fromPainelSolar)
                    .toList());
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        painelSolarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
