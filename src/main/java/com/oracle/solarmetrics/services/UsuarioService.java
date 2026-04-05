package com.oracle.solarmetrics.services;

import com.oracle.solarmetrics.domains.Usuario;
import com.oracle.solarmetrics.gateways.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(usuario.getRoles().toArray(new String[0]))
                .build();
    }
}
