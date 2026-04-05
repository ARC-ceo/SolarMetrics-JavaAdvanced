package com.oracle.solarmetrics.gateways.dtos.sensorDto;

import com.oracle.solarmetrics.domains.Sensor;

public record SensorResponseDto(
        String id,
        String status,
        String sistemaId,
        String tipo,
        String localizacao
) {
    public static SensorResponseDto fromSensor(Sensor sensor){
        return new SensorResponseDto (
                sensor.getId(),
                sensor.getStatus(),
                sensor.getSistema().getId(),
                sensor.getTipo(),
                sensor.getLocalizacao()
        );
    }
}
