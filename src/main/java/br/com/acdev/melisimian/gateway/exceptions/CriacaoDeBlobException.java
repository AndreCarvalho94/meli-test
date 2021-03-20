package br.com.acdev.melisimian.gateway.exceptions;

public class CriacaoDeBlobException extends RuntimeException {

    public CriacaoDeBlobException(){
        super("Nao foi possivel salvar este DNA na base de dados");
    }
}
