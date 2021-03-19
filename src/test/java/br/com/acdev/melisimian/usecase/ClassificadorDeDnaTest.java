package br.com.acdev.melisimian.usecase;

import br.com.acdev.melisimian.domain.Dna;
import br.com.acdev.melisimian.domain.Pontuacao;
import br.com.acdev.melisimian.usecase.impl.ClassificadorDeDnaImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClassificadorDeDnaTest {

    @Test
    public void deve_classificar_dna_simio() {
        ClassificadorDeDnaImpl classificarDna = new ClassificadorDeDnaImpl();
        List<String> dna = Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG");
        Assert.assertTrue(classificarDna.isSimio(new Dna(dna)));
    }

    @Test
    public void deve_achar_1_sequencia_CCCC_na_horizontal_test() {
        ClassificadorDeDnaImpl classificarDna = new ClassificadorDeDnaImpl();
        Dna dna = new Dna(Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"));
        Pontuacao pontuacao = classificarDna.verificaIgualdadeEmSequenciaHorizontal(4, dna);
        Assert.assertEquals(1, pontuacao.getPontuacao('C'));
    }

    @Test
    public void deve_achar_1_sequencia_CCCC_na_horizontal_com_palavra_CCCCCC_test() {
        ClassificadorDeDnaImpl classificarDna = new ClassificadorDeDnaImpl();
        Dna dna = new Dna(Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCCC", "TCACTG"));
        Pontuacao pontuacao = classificarDna.verificaIgualdadeEmSequenciaHorizontal(4, dna);
        Assert.assertEquals(1, pontuacao.getPontuacao('C'));
    }

    @Test
    public void deve_achar_2_sequencias_CCCC_na_horizontal_com_palavra_CCCCCCCC_test() {
        ClassificadorDeDnaImpl classificarDna = new ClassificadorDeDnaImpl();
        Dna dna = new Dna(Arrays.asList("CTGAGACT", "CTATGCAC", "TATTGTGT", "AGAGGGAT", "CCCCCCCC", "TCACTGCT"));
        Pontuacao pontuacao = classificarDna.verificaIgualdadeEmSequenciaHorizontal(4, dna);
        Assert.assertEquals(2, pontuacao.getPontuacao('C'));
    }


    /**
     * C T G A G A C T
     * C T A T G C A T
     * T A T T G T G T
     * A G A G G G A T
     */
    @Test
    public void is_simio_test(){
        ClassificadorDeDnaImpl classificarDna = new ClassificadorDeDnaImpl();
        Dna dna = new Dna(Arrays.asList("CTGAGACT", "CTATGCAT", "TATTGTGT", "AGAGGGAT"));
        Assert.assertTrue(classificarDna.isSimio(dna));
    }
}
