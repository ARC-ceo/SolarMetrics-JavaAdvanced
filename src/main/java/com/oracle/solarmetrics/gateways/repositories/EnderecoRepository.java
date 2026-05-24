package com.oracle.solarmetrics.gateways.repositories;

import com.oracle.solarmetrics.domains.Endereco;
import com.oracle.solarmetrics.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,String> {
}
