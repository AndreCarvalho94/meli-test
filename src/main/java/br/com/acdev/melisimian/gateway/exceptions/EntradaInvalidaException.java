package br.com.acdev.melisimian.gateway.exceptions;

public class EntradaInvalidaException extends RuntimeException {

    public EntradaInvalidaException(){
        super("Entrada invalida");
    }
}
