package com.oracle.solarmetrics.gateways;

import com.oracle.solarmetrics.gateways.dtos.ClienteRequestDto;
import com.oracle.solarmetrics.gateways.dtos.ClienteRequestPatchDto;
import com.oracle.solarmetrics.gateways.dtos.ClienteRequestUpdateDto;
import com.oracle.solarmetrics.gateways.dtos.response.ClienteResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ClienteControllerInterface {

    ResponseEntity<ClienteResponseDto> create(ClienteRequestDto clienteRequestDto);
    ResponseEntity<ClienteResponseDto> update(ClienteRequestUpdateDto clienteRequestUpdateDto);
    ResponseEntity<ClienteResponseDto> getId(String id);
    ResponseEntity<List<ClienteResponseDto>> getAll();
    ResponseEntity<ClienteResponseDto> patch(String id, ClienteRequestPatchDto clienteRequestPatchDto);
    ResponseEntity<Void> delete(String id);
}
