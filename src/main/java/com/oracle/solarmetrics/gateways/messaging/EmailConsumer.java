package com.oracle.solarmetrics.gateways.messaging;

import com.oracle.solarmetrics.gateways.dtos.emailDto.EmailQueueDto;
import com.oracle.solarmetrics.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "email-welcome")
    public void consume(EmailQueueDto dto) {
        try {
            emailService.sendEmail(dto.getEmail(), dto.getName());
        } catch (Exception e) {
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }
}
