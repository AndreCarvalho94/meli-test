package br.com.acdev.melisimian.components;

import br.com.acdev.melisimian.gateway.controller.dto.DnaRequest;

public interface ValidadorDeDna {
    void executar(DnaRequest dna);
}
