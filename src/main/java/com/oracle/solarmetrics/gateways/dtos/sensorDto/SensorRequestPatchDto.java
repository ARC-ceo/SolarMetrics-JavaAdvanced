package com.oracle.solarmetrics.gateways.dtos.sensorDto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.oracle.solarmetrics.domains.Sensor;
import com.oracle.solarmetrics.domains.Sistema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SensorRequestPatchDto {

    @NotBlank(message = "O MAC Address é obrigatório")
    @Pattern(
            regexp = "^([0-9A-Fa-f]{2}:){5}([0-9A-Fa-f]{2})$",
            message = "O MAC Address deve seguir o padrão 00:AA:11:BB:22:CC"
    )
    private String macAddress;

    @JsonSetter("macAddress")
    public void formatMacAddress(String macAddress) {
        this.macAddress = (macAddress != null)
                ? macAddress.trim().toUpperCase()
                : null;
    }

    private String tipo;

    private String status;

    private String localizacao;

    private String sistemaId;

    public Sensor toSensor(){
        return Sensor.builder()
                .id(macAddress)
                .tipo(tipo)
                .status(status)
                .localizacao(localizacao)
                .sistema(Sistema.builder()
                        .id(sistemaId)
                        .build())
                .build();
    }
}
