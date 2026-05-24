package com.oracle.solarmetrics.gateways.repositories;

import com.oracle.solarmetrics.domains.Cidade;
import com.oracle.solarmetrics.domains.Estado;
import com.oracle.solarmetrics.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade,String> {
    Optional<Cidade> findByNomeAndEstado(String localidade, Estado estado);
}
