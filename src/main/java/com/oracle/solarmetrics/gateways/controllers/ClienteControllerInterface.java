package com.oracle.solarmetrics.gateways.controllers;

import com.oracle.solarmetrics.gateways.dtos.clienteDto.ClienteRequestDto;
import com.oracle.solarmetrics.gateways.dtos.clienteDto.ClienteRequestPatchDto;
import com.oracle.solarmetrics.gateways.dtos.clienteDto.ClienteRequestUpdateDto;
import com.oracle.solarmetrics.gateways.dtos.clienteDto.ClienteResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteControllerInterface {

    ResponseEntity<ClienteResponseDto> create(ClienteRequestDto clienteRequestDto);
    ResponseEntity<ClienteResponseDto> update(ClienteRequestUpdateDto clienteRequestUpdateDto);
    ResponseEntity<ClienteResponseDto> getId(String id);
    ResponseEntity<List<ClienteResponseDto>> getAll();
    ResponseEntity<ClienteResponseDto> patch(String id, ClienteRequestPatchDto clienteRequestPatchDto);
    ResponseEntity<Void> delete(String id);
}
