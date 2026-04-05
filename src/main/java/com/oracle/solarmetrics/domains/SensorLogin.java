package com.oracle.solarmetrics.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.NumericBooleanConverter;

import java.util.List;

@With
@Getter
@Entity(name = "SM_SENSOR_LOGIN")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class SensorLogin {
    @Id
    private String username;
    private String password;
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isSuperuser;

}