package com.oracle.solarmetrics.services;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.domains.Usuario;
import com.oracle.solarmetrics.gateways.ClienteRepository;
import com.oracle.solarmetrics.gateways.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteServiceInterface {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public Cliente create(Cliente cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("O e-mail já está em uso.");
        }
        Cliente clienteCodificado = Cliente.builder()
                .email(cliente.getEmail())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .tipoUser(cliente.getTipoUser())
                .usuario(Usuario.builder()
                        .username(cliente.getUsuario().getUsername())
                        .password(passwordEncoder.encode(cliente.getUsuario().getPassword()))
                        .build())
                .build();
        return clienteRepository.save(clienteCodificado);
    }

    public Cliente update(Cliente cliente) {
        getId(cliente.getId());
        if (clienteRepository.findByEmail(cliente.getEmail()).filter(c -> !c.getId().equals(cliente.getId())).isPresent()) {
            throw new IllegalArgumentException("O e-mail já está em uso.");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente getId(String id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new EntityNotFoundException("Usuario não encontrado.");
        }
        return cliente.get();
    }

    public void delete(String id) {
        getId(id);
        clienteRepository.deleteById(id);
    }

    public Cliente patch(String id, Cliente cliente) {
        Cliente clienteExistente = getId(id);
        Cliente clienteAtualizado = Cliente.builder()
                .id(clienteExistente.getId())
                .nome(cliente.getNome() != null ? cliente.getNome() : clienteExistente.getNome())
                .email(cliente.getEmail() != null ? cliente.getEmail() : clienteExistente.getEmail())
                .telefone(cliente.getTelefone() != null ? cliente.getTelefone() : clienteExistente.getTelefone())
                .tipoUser(clienteExistente.getTipoUser())
                .build();
        if (clienteRepository.findByEmail(clienteAtualizado.getEmail()).filter(c -> !c.getId().equals(clienteAtualizado.getId())).isPresent()) {
            throw new IllegalArgumentException("O e-mail já está em uso.");
        }
        return clienteRepository.save(clienteAtualizado);
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
}
