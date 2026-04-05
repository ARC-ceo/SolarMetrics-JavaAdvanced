package com.oracle.solarmetrics.gateways.controllers;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.oracle.solarmetrics.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PublicKey publicKey;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestHeader("Authorization") String basicAuth) {

        var decoded = new String(Base64.getDecoder().decode(basicAuth.substring(6)));
        var parts = decoded.split(":");
        String username = parts[0];
        String password = parts[1];

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(Map.of(
                "access_token", token
        ));
    }

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getKeys() {

        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;

        RSAKey rsaKey = new RSAKey.Builder(rsaPublicKey).build();

        JWKSet jwkSet = new JWKSet(rsaKey);

        return jwkSet.toJSONObject();
    }

}
