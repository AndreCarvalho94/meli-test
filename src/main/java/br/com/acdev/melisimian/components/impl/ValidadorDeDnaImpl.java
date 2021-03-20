package br.com.acdev.melisimian.components.impl;

import br.com.acdev.melisimian.components.ValidadorDeDna;
import br.com.acdev.melisimian.gateway.controller.dto.DnaRequest;
import br.com.acdev.melisimian.gateway.exceptions.EntradaInvalidaException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeDnaImpl implements ValidadorDeDna {
    @Override
    public void executar(DnaRequest dna) {

        if (dna.getDna().size() == 0) {
            throw new EntradaInvalidaException();
        }
        if (dna.getDna().size() != dna.getDna().get(0).length()) {
            throw new EntradaInvalidaException();
        }
        String sequencia = String.join("", dna.getDna());
        if (!sequencia.matches("^([ATCG])*$")) {
            throw new EntradaInvalidaException();
        }

    }
}
