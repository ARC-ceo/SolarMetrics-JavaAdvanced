package com.oracle.solarmetrics.gateways;

import com.oracle.solarmetrics.gateways.dtos.SistemaRequestDto;
import com.oracle.solarmetrics.gateways.dtos.SistemaRequestPatchDto;
import com.oracle.solarmetrics.gateways.dtos.SistemaRequestUpdateDto;
import com.oracle.solarmetrics.gateways.dtos.response.SistemaResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SistemaControllerInterface {

    ResponseEntity<SistemaResponseDto> create(SistemaRequestDto sistemaRequestDto);
    ResponseEntity<SistemaResponseDto> update(SistemaRequestUpdateDto sistemaRequestUpdateDto);
    ResponseEntity<SistemaResponseDto> getId(String id);
    ResponseEntity<List<SistemaResponseDto>> getAll();
    ResponseEntity<List<SistemaResponseDto>> getSistemasCliente(String id);
    ResponseEntity<SistemaResponseDto> patch(String id, SistemaRequestPatchDto sistemaRequestPatchDto);
    ResponseEntity<Void> delete(String id);

}
