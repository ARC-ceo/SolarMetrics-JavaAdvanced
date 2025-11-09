package com.oracle.solarmetrics.gateways;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    public String getToken(Authentication authentication) {
        return authentication.getName();
    }

}
