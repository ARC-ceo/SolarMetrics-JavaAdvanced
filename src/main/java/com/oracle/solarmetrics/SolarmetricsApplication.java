package com.oracle.solarmetrics;

import com.oracle.solarmetrics.domains.Usuario;
import com.oracle.solarmetrics.gateways.UsuarioRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                title = "API SolarMetrics",
                version = "1.0",
                description = "Documentação automática gerada pelo Swagger para o projeto de energia renovável"
        )
)
public class SolarmetricsApplication {


    public static void main(String[] args) {
		SpringApplication.run(SolarmetricsApplication.class, args);
	}


}
