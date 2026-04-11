package com.oracle.solarmetrics.gateways.controllers;

import com.oracle.solarmetrics.domains.Sensor;
import com.oracle.solarmetrics.gateways.dtos.sensorDto.SensorRequestDto;
import com.oracle.solarmetrics.gateways.dtos.sensorDto.SensorRequestLoginDto;
import com.oracle.solarmetrics.gateways.dtos.sensorDto.SensorRequestPatchDto;
import com.oracle.solarmetrics.gateways.dtos.sensorDto.SensorResponseDto;
import com.oracle.solarmetrics.services.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SensorResponseDto> create(@RequestBody @Valid SensorRequestDto sensorRequestDto) {
        Sensor sensor = sensorService.create(sensorRequestDto.toSensor());
        return ResponseEntity.status(HttpStatus.CREATED).body(SensorResponseDto.fromSensor(sensor));
    }

    @PostMapping("/auth")
    public ResponseEntity<Void> auth(@RequestBody @Valid SensorRequestLoginDto sensorRequestLoginDto) {
        sensorService.getLogin(sensorRequestLoginDto.toSensor());
        return ResponseEntity.ok().body(null);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SensorResponseDto> update(@RequestBody @Valid SensorRequestDto sensorRequestDto) {
        Sensor sensor = sensorService.update(sensorRequestDto.toSensor());
        return ResponseEntity.ok().body(SensorResponseDto.fromSensor(sensor));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<SensorResponseDto> getId(@PathVariable("id") String id) {
        Sensor sensor = sensorService.getId(id);
        return ResponseEntity.ok().body(SensorResponseDto.fromSensor(sensor));
    }

    @GetMapping(value = "/sistema/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<SensorResponseDto> getSensoresSistema(@PathVariable("id") String id) {
        Sensor sensor = sensorService.getSensoresSistema(id);
        return ResponseEntity.ok(SensorResponseDto.fromSensor(sensor));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<SensorResponseDto>> getAll() {
        List<Sensor> sensors = sensorService.getAll();
        if (sensors.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity.ok(sensors.stream()
                    .map(SensorResponseDto::fromSensor)
                    .toList());
        }
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SensorResponseDto> patch(@PathVariable("id") String id, @RequestBody @Valid SensorRequestPatchDto sensorRequestPatchDto) {
        Sensor sensor = sensorService.patch(id, sensorRequestPatchDto.toSensor());
        return ResponseEntity.ok().body(SensorResponseDto.fromSensor(sensor));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        sensorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
