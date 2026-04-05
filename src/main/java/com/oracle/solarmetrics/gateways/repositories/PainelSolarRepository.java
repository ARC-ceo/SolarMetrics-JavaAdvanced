package com.oracle.solarmetrics.gateways.repositories;

import com.oracle.solarmetrics.domains.PainelSolar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PainelSolarRepository extends JpaRepository<PainelSolar, String> {

    List<PainelSolar> findBySistema_Id(String sistemaId);
}
