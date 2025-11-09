package com.oracle.solarmetrics.services;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.domains.Sistema;
import com.oracle.solarmetrics.exceptions.SistemaJaExistenteException;
import com.oracle.solarmetrics.gateways.ClienteRepository;
import com.oracle.solarmetrics.gateways.SistemaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SistemaService implements SistemaServiceInterface {

    private final SistemaRepository sistemaRepository;
    private final ClienteRepository clienteRepository;

    public Sistema create(Sistema sistema) {
        getCliente(sistema.getCliente().getId());
        Optional<Sistema> resultSistema = sistemaRepository.findByNomeInstalacaoAndCliente_Id(sistema.getNomeInstalacao(), sistema.getCliente().getId());
        if (resultSistema.isPresent()) {
            throw new SistemaJaExistenteException("O usuário já possui uma instalação com este nome.");
        }
        return sistemaRepository.save(sistema);
    }

    public Sistema update(Sistema sistema) {
        getId(sistema.getId());
        getCliente(sistema.getCliente().getId());
        if (sistemaRepository.findByNomeInstalacaoAndCliente_Id(sistema.getNomeInstalacao(), sistema.getCliente().getId()).filter(s -> !s.getId().equals(sistema.getId())).isPresent()) {
            throw new SistemaJaExistenteException("O usuário já possui uma instalação com este nome.");
        }
        return sistemaRepository.save(sistema);
    }

    public void delete(String id) {
        getId(id);
        sistemaRepository.deleteById(id);
    }

    public Cliente getCliente(String id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new EntityNotFoundException("Usuario não encontrado.");
        }
        return cliente.get();
    }

    public Sistema getId(String id) {
        Optional<Sistema> sistema = sistemaRepository.findById(id);
        if (sistema.isEmpty()) {
            throw new EntityNotFoundException("Sistema não encontrado.");
        }
        return sistema.get();
    }

    public List<Sistema> getSistemasCliente(String clienteId) {
        getCliente(clienteId);
        return sistemaRepository.findByCliente_Id(clienteId);
    }

    public Sistema patch(String id, Sistema sistema) {
        Sistema sistemaExistente = getId(id);
        Sistema sistemaAtualizado = Sistema.builder()
                .id(sistemaExistente.getId())
                .nomeInstalacao(sistema.getNomeInstalacao() != null
                        ? sistema.getNomeInstalacao()
                        : sistemaExistente.getNomeInstalacao())
                .dataInstalacao(sistema.getDataInstalacao() != null
                        ? sistema.getDataInstalacao()
                        : sistemaExistente.getDataInstalacao())
                .potenciaTotal(sistema.getPotenciaTotal() != null
                        ? sistema.getPotenciaTotal()
                        : sistemaExistente.getPotenciaTotal())
                .status(sistema.getStatus() != null
                        ? sistema.getStatus()
                        : sistemaExistente.getStatus())
                .status(sistema.getStatus() != null
                        ? sistema.getStatus()
                        : sistemaExistente.getStatus())
                .cliente(Cliente.builder().id(sistemaExistente.getCliente().getId()).build())
                .build();
        if (sistemaRepository.findByNomeInstalacaoAndCliente_Id(sistemaAtualizado.getNomeInstalacao(), sistemaAtualizado.getCliente().getId()).filter(s -> !s.getId().equals(sistemaAtualizado.getId())).isPresent()) {
            throw new SistemaJaExistenteException("O usuário já possui uma instalação com este nome.");
        }
        return sistemaRepository.save(sistemaAtualizado);
    }

    public List<Sistema> getAll(){
        return sistemaRepository.findAll();
    }
}
