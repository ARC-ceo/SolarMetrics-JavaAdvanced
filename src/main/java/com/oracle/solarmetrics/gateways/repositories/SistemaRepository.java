package com.oracle.solarmetrics.gateways.repositories;

import com.oracle.solarmetrics.domains.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SistemaRepository extends JpaRepository<Sistema, String> {

    Optional<Sistema> findByNomeInstalacaoAndCliente_Id (String nomeInstalacao, String clienteId);

    List<Sistema> findByCliente_Id(String clienteId);

    @Query("SELECT s.id FROM SM_SISTEMA s WHERE s.cliente.id = :clienteId")
    List<String> findIdsByClienteId(String clienteId);
}
