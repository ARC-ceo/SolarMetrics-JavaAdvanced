package com.oracle.solarmetrics;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
