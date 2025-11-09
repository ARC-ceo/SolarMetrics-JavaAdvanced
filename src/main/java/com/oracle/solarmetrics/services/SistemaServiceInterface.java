package com.oracle.solarmetrics.services;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.domains.Sistema;

import java.util.List;

public interface SistemaServiceInterface {

    Sistema create(Sistema sistema);
    Sistema update(Sistema sistema);
    void delete(String id);
    Cliente getCliente(String id);
    Sistema getId(String id);
    List<Sistema> getSistemasCliente(String clienteId);
    Sistema patch(String id, Sistema sistema);
    List<Sistema> getAll();

}
