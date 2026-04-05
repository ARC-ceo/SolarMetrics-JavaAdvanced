package com.oracle.solarmetrics.gateways.dtos.sensorDto;

import com.oracle.solarmetrics.domains.Sensor;
import com.oracle.solarmetrics.domains.SensorLogin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SensorRequestLoginDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Sensor toSensor(){
        return Sensor.builder()
                .sensorLogin(SensorLogin.builder()
                        .username(username)
                        .password(password)
                        .build())
                .build();
    }
}
