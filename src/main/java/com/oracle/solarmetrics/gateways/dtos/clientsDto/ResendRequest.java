package com.oracle.solarmetrics.gateways.dtos.clientsDto;
import java.util.List;

public class ResendRequest {

    public String from;
    public List<String> to;
    public String subject;
    public String html;
}