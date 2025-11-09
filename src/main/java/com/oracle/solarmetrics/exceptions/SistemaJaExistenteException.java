package com.oracle.solarmetrics.exceptions;

public class SistemaJaExistenteException extends RuntimeException {

    public SistemaJaExistenteException(String mensagem) {
        super(mensagem);
    }
}