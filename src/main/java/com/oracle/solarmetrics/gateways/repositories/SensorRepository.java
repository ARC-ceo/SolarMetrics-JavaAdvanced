package com.oracle.solarmetrics.gateways.repositories;

import com.oracle.solarmetrics.domains.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, String> {

    Optional<Sensor> findBySistema_Cliente_Id(String id);

    Optional<Sensor> findById(String id);

    Optional<Sensor> findBySistema_Id(String sistemaId);

    Optional<Sensor> findBySensorLogin_Username(String username);
}

