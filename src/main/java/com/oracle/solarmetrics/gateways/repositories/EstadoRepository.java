package com.oracle.solarmetrics.gateways.repositories;

import com.oracle.solarmetrics.domains.Estado;
import com.oracle.solarmetrics.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado,String> {
    Optional<Estado> findByUf(String uf);
}
