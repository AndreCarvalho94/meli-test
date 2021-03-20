package br.com.acdev.melisimian.core.exceptions;

public class DnaJaExistenteException extends RuntimeException {

    public DnaJaExistenteException(){
        super("Este DNA ja foi classificado e armazenado na base de dados");
    }

}
