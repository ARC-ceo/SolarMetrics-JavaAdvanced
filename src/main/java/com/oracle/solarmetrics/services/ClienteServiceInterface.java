package com.oracle.solarmetrics.services;

import com.oracle.solarmetrics.domains.Cliente;

import java.util.List;

public interface ClienteServiceInterface {

    Cliente create(Cliente cliente);
    Cliente update(Cliente cliente);
    Cliente getId(String id);
    void delete(String id);
    Cliente patch(String id, Cliente cliente);
    List<Cliente> getAll();

}
