package com.oracle.solarmetrics.services;

import com.oracle.solarmetrics.domains.Cliente;
import com.oracle.solarmetrics.domains.Sensor;
import com.oracle.solarmetrics.gateways.repositories.ClienteRepository;
import com.oracle.solarmetrics.gateways.repositories.SensorRepository;
import com.oracle.solarmetrics.gateways.repositories.SistemaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

@Service
public class JwtService {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final ClienteRepository clienteRepository;
    private final SistemaRepository sistemaRepository;

    public JwtService(PrivateKey privateKey, PublicKey publicKey,ClienteRepository clienteRepository, SistemaRepository sistemaRepository) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.clienteRepository = clienteRepository;
        this.sistemaRepository = sistemaRepository;
    }

    public String generateToken(UserDetails user) {
        Cliente usuarioInfo = clienteRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        List<String> sistemaIds = sistemaRepository.findIdsByClienteId(usuarioInfo.getId());
        List<String> topics = sistemaIds.stream()
                .map(id -> "devices/" + id + "/realtime")
                .toList();

        Map<String, Object> acl = new HashMap<>();
        acl.put("sub", topics);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("nome", usuarioInfo.getNome())
                .claim("id", usuarioInfo.getId())
                .claim("username", "EmqxJwtUser")
                .claim("acl", acl)
                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
