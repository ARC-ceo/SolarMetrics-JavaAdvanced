package com.oracle.solarmetrics.gateways.repositories;

import com.oracle.solarmetrics.domains.SensorLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorLoginRepository extends JpaRepository<SensorLogin, String> {
}
