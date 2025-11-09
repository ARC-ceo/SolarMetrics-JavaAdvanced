package com.oracle.solarmetrics.gateways;

import com.oracle.solarmetrics.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
