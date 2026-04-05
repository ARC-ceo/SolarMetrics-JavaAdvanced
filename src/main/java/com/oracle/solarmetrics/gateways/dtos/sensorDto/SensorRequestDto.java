package com.oracle.solarmetrics.gateways.dtos.sensorDto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.oracle.solarmetrics.domains.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.util.DigestUtils;

@Data
public class SensorRequestDto {

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

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @NotBlank(message = "A localização é obrigatória")
    private String localizacao;

    @NotBlank(message = "Informe o sistema para vincular o sensor")
    private String sistemaId;

    public Sensor toSensor(){
        return Sensor.builder()
                .id(macAddress)
                .tipo(tipo)
                .status(status)
                .localizacao(localizacao)
                .sensorLogin(SensorLogin.builder()
                        .username(macAddress)
                        .password(DigestUtils.md5DigestAsHex(macAddress.getBytes()))
                        .build())
                .sistema(Sistema.builder()
                        .id(sistemaId)
                        .build())
                .build();
    }

}
