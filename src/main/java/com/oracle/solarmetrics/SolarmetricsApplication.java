package com.oracle.solarmetrics;

import com.oracle.solarmetrics.domains.Usuario;
import com.oracle.solarmetrics.gateways.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@EnableFeignClients
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

    @Bean
    public CommandLineRunner createAdmin(UsuarioRepository usuarioRepository,
                                         PasswordEncoder passwordEncoder) {
        return args -> {

            String username = "admin@solarmetrics.com";

            if (usuarioRepository.findById(username).isEmpty()) {
                Usuario admin = Usuario.builder()
                        .username("admin@solarmetrics.com")
                        .password(passwordEncoder.encode("admin"))
                        .roles(List.of("ROLE_ADMIN"))
                        .build();

                usuarioRepository.save(admin);
                System.out.println("Admin criado!");
            }
        };
    }
}
