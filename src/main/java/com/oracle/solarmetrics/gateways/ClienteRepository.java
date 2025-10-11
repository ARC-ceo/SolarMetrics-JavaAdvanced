package com.oracle.solarmetrics.gateways;

import com.oracle.solarmetrics.domains.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    Optional<Cliente> findByEmail(String email);

}
