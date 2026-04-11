package com.oracle.solarmetrics.services;
import com.oracle.solarmetrics.gateways.client.EmailClient;
import com.oracle.solarmetrics.gateways.dtos.clientsDto.ResendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient resendClient;
    private final TemplateEngine templateEngine;

    @Value("${resend.api-key}")
    private String apiKey;

    public void sendEmail(String toEmail, String nome) {

        Context context = new Context();
        context.setVariable("nome", nome);
        String html = templateEngine.process("welcome-email", context);

        ResendRequest request = new ResendRequest();
        request.from = "no-reply@grouparc.com.br";
        request.to = List.of(toEmail);
        request.subject = "Bem-vindo ao SolarMetrics!";
        request.html = html;

        resendClient.sendEmail(
                "Bearer " + apiKey,
                request
        );
    }
}