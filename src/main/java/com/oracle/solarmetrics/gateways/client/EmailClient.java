package com.oracle.solarmetrics.gateways.client;

import com.oracle.solarmetrics.gateways.dtos.clientsDto.ResendRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "resend",
        url = "https://api.resend.com"
)
public interface EmailClient {

    @PostMapping("/emails")
    void sendEmail(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ResendRequest request
    );
}
