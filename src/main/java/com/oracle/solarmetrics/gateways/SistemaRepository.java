package com.oracle.solarmetrics.gateways;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.domains.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SistemaRepository extends JpaRepository<Sistema, String> {

    Optional<Sistema> findByNomeInstalacaoAndCliente_Id (String nomeInstalacao, String clienteId);

    List<Sistema> findByCliente_Id(String clienteId);
}
