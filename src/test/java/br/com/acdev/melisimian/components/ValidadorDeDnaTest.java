package br.com.acdev.melisimian.components;

import br.com.acdev.melisimian.components.impl.ValidadorDeDnaImpl;
import br.com.acdev.melisimian.gateway.controller.dto.DnaRequest;
import br.com.acdev.melisimian.gateway.exceptions.EntradaInvalidaException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ValidadorDeDnaTest {

    ValidadorDeDna validadorDeDna = new ValidadorDeDnaImpl();

    @Test(expected = EntradaInvalidaException.class)
    public void deve_dar_erro_por_dna_vazio(){
        DnaRequest dnaRequest = new DnaRequest(Collections.singletonList(""));
        validadorDeDna.executar(dnaRequest);
    }

    @Test(expected = EntradaInvalidaException.class)
    public void deve_dar_erro_por_matriz_com_1_linha(){
        DnaRequest dnaRequest = new DnaRequest(Collections.singletonList("ATCG"));
        validadorDeDna.executar(dnaRequest);
    }

    @Test(expected = EntradaInvalidaException.class)
    public void deve_dar_erro_por_matriz_com_2_linhas(){
        DnaRequest dnaRequest = new DnaRequest(Arrays.asList("ATCG", "ATCG"));
        validadorDeDna.executar(dnaRequest);
    }

    @Test(expected = EntradaInvalidaException.class)
    public void deve_dar_erro_por_bases_diferentes_de_ATCG(){
        DnaRequest dnaRequest = new DnaRequest(Arrays.asList("ATCG", "ATCG","ATCG","XATC"));
        validadorDeDna.executar(dnaRequest);
    }
}
