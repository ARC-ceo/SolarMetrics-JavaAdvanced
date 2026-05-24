package com.oracle.solarmetrics.gateways.controllers;

import com.oracle.solarmetrics.gateways.dtos.sistemaDto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SistemaControllerInterface {

    ResponseEntity<SistemaResponseDto> create(SistemaRequestDto sistemaRequestDto);
    ResponseEntity<SistemaResponseDto> update(SistemaRequestUpdateDto sistemaRequestUpdateDto);
    ResponseEntity<SistemaResponseIdDto> getId(String id);
    ResponseEntity<List<SistemaResponseDto>> getAll();
    ResponseEntity<List<SistemaResponseIdDto>> getSistemasCliente(String id);
    ResponseEntity<SistemaResponseDto> patch(String id, SistemaRequestPatchDto sistemaRequestPatchDto);
    ResponseEntity<Void> delete(String id);

}
