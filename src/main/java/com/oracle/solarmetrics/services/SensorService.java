package com.oracle.solarmetrics.services;

import com.oracle.solarmetrics.domains.*;
import com.oracle.solarmetrics.gateways.repositories.SensorRepository;
import com.oracle.solarmetrics.gateways.repositories.SistemaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SistemaRepository sistemaRepository;

    public Sensor create(Sensor sensor) {
        if (sensorRepository.findById("SM_" + sensor.getId()).isPresent()) {
            throw new IllegalArgumentException("O sensor já está cadastrado.");
        }
        getSistema(sensor.getSistema().getId());
        if (sensorRepository.findBySistema_Id(sensor.getSistema().getId()).isPresent()) {
            throw new IllegalArgumentException("O sistema já está vinculado a outro sensor.");
        }
        return sensorRepository.save(sensor);
    }

    public Sensor update(Sensor sensor) {
        getId(sensor.getId());
        if (sensorRepository.findBySistema_Id(sensor.getSistema().getId()).isPresent()) {
            throw new IllegalArgumentException("O sistema já está vinculado a outro sensor.");
        }
        return sensorRepository.save(sensor);
    }

    public Sensor getId(String id) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        if (sensor.isEmpty()) {
            throw new EntityNotFoundException("Sensor não encontrado.");
        }
        return sensor.get();
    }

    public void getSistema(String id) {
        Optional<Sistema> sistema = sistemaRepository.findById(id);
        if (sistema.isEmpty()) {
            throw new EntityNotFoundException("Sistema não encontrado.");
        }
    }

    public void getLogin(Sensor sensor) {
        Optional<Sensor> sensorItem = sensorRepository.findBySensorLogin_Username(sensor.getSensorLogin().getUsername());
        if (sensorItem.isEmpty() || !sensorItem.get().getSensorLogin().getPassword().equals(sensor.getSensorLogin().getPassword())) {
            throw new EntityNotFoundException("Sensor não encontrado.");
        }
    }

    public Sensor getSensoresSistema(String sistemaId) {
        getSistema(sistemaId);
        Optional<Sensor> sensor = sensorRepository.findBySistema_Id(sistemaId);
        if  (sensor.isEmpty()) {
            throw new EntityNotFoundException("Sensor não encontrado.");
        }
        return sensor.get();
    }

    public void delete(String id) {
        getId(id);
        sensorRepository.deleteById(id);
    }

    public Sensor patch(String id, Sensor sensor) {
        Sensor sensorExistente = getId(id);
        Sensor sensorAtualizado = Sensor.builder()
                .id(sensorExistente.getId())
                .tipo(sensor.getTipo() != null ? sensor.getTipo() : sensorExistente.getTipo())
                .status(sensor.getStatus() != null ? sensor.getStatus() : sensorExistente.getStatus())
                .localizacao(sensor.getLocalizacao() != null ? sensor.getLocalizacao() : sensorExistente.getLocalizacao())
                .sistema(Sistema.builder()
                        .id(sensor.getSistema().getId() != null ? sensor.getSistema().getId() : sensorExistente.getSistema().getId())
                        .build())
                .build();

        getSistema(sensorAtualizado.getSistema().getId());
        if (sensorRepository.findBySistema_Id(sensorAtualizado.getSistema().getId()).filter(c -> !c.getId().equals(sensorAtualizado.getSistema().getId())).isPresent()) {
            throw new IllegalArgumentException("O sistema já está vinculado a outro sensor.");
        }

        return sensorRepository.save(sensorAtualizado);
    }

    public List<Sensor> getAll() {
        return sensorRepository.findAll();
    }
}