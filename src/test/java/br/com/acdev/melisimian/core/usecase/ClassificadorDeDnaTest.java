package br.com.acdev.melisimian.core.usecase;

import br.com.acdev.melisimian.core.dataprovider.DnaRepository;
import br.com.acdev.melisimian.core.entity.DnaEntity;
import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.core.model.Estatisticas;
import br.com.acdev.melisimian.core.model.Pontuacao;
import br.com.acdev.melisimian.core.usecase.impl.ClassificadorDeDnaImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClassificadorDeDnaTest {

    private DnaRepository dnaRepository = (new DnaRepository() {
        @Override
        public DnaEntity salvar(Dna dna, boolean isSimio) {
            return new DnaEntity();
        }

        @Override
        public Estatisticas consultarEstatisticas() {
            return null;
        }
    });

    ClassificadorDeDnaImpl classificadorDeDna = new ClassificadorDeDnaImpl(dnaRepository);

    @Test
    public void deve_classificar_dna_simio() {
        List<String> dna = Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG");
        Assert.assertTrue(classificadorDeDna.isSimio(new Dna(dna)));
    }

    @Test
    public void deve_achar_1_sequencia_CCCC_na_horizontal_test() {
        Dna dna = new Dna(Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"));
        Pontuacao pontuacao = classificadorDeDna.verificarIgualdadeEmSequenciaHorizontal(dna);
        Assert.assertEquals(1, pontuacao.getPontuacao('C'));
    }

    @Test
    public void deve_achar_1_sequencia_CCCC_na_horizontal_com_palavra_CCCCCC_test() {
        Dna dna = new Dna(Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCCC", "TCACTG"));
        Pontuacao pontuacao = classificadorDeDna.verificarIgualdadeEmSequenciaHorizontal(dna);
        Assert.assertEquals(1, pontuacao.getPontuacao('C'));
    }

    @Test
    public void deve_achar_2_sequencias_CCCC_na_horizontal_com_palavra_CCCCCCCC_test() {
        Dna dna = new Dna(Arrays.asList("CTGAGACT", "CTATGCAC", "TATTGTGT", "AGAGGGAT", "CCCCCCCC", "TCACTGCT"));
        Pontuacao pontuacao = classificadorDeDna.verificarIgualdadeEmSequenciaHorizontal(dna);
        Assert.assertEquals(2, pontuacao.getPontuacao('C'));
    }

    @Test
    public void deve_achar_2_sequencias_na_linha_5() {
        Dna dna = new Dna(Arrays.asList("CTGAGACT", "CTATGCAC", "TATTGTGT", "AGAGGGAT", "CCCCCCCC", "TCACTGCT"));
        Pontuacao pontuacao =
                classificadorDeDna.verificaIgualdadeEmDirecao(
                        dna, 4, 0, 0, 1, dna.tamanhoPalavras());
        Assert.assertEquals(2, pontuacao.getPontuacao('C'));
    }

    /**
     * A T C G A T C A
     * T A T C G A T C
     * T T A T C G C G
     * C G T A A T T A
     * T T A T C G G T
     * A C A C G A G G
     **/
    @Test
    public void deve_achar_sequencia_em_diagonal_primaria_linha_2() {
        Dna dna = new Dna(Arrays.asList(
                "ATCGATCA",
                "TATCGATC",
                "TTATCGAG",
                "CGTAGTTA",
                "TTATCGGT",
                "ACACGAGG"));
        Pontuacao pontuacao =
                classificadorDeDna.verificarIgualdadeEmDiagonalPrimaria(dna);
        Assert.assertEquals(1, pontuacao.getPontuacao('T'));
    }

    @Test
    public void deve_achar_sequencia_em_diagonal_primaria_linha_3() {
        Dna dna = new Dna(Arrays.asList(
                "TTCGATCA",
                "TCTCGATC",
                "TTATCGAG",
                "CGTAATTA",
                "TTATAGGT",
                "ACACGAGG"));
        Pontuacao pontuacao =
                classificadorDeDna.verificarIgualdadeEmDiagonalPrimaria(dna);
        Assert.assertEquals(1, pontuacao.getPontuacao('T'));
    }

    @Test
    public void deve_achar_sequencia_em_diagonal_secundaria_caso_simples() {
        Dna dna = new Dna(Arrays.asList(
                "TTCT",
                "TCTC",
                "TTAT",
                "TGTA"));
        Pontuacao pontuacao = classificadorDeDna.verificaIgualdadeEmDiagonalSecundaria(dna);
        Assert.assertEquals(1, pontuacao.getPontuacao('T'));
    }

    /**
     * A T C G A T C A
     * T A T C G A T C
     * T T A T G A C G
     * C G T G A C T A
     * T T G A C G G T
     * T G A C G A G G
     **/
    @Test
    public void deve_achar_sequencia_em_diagonal_secundaria_todas_as_bases() {
        Dna dna = new Dna(Arrays.asList(
                "ATCGATCA",
                "TATCGATC",
                "TTATGACG",
                "CGTGACTA",
                "TTGACGGT",
                "TGACGAGG"));
        Pontuacao pontuacao = classificadorDeDna.verificaIgualdadeEmDiagonalSecundaria(dna);
        Assert.assertEquals(1, pontuacao.getPontuacao('A'));
        Assert.assertEquals(1, pontuacao.getPontuacao('T'));
        Assert.assertEquals(1, pontuacao.getPontuacao('C'));
        Assert.assertEquals(1, pontuacao.getPontuacao('G'));
    }

    /**
     * C T G A G A C T
     * C T A T G C A T
     * T A T T G T G T
     * A G A G G G A T
     */
    @Test
    public void is_simio_test() {
        Dna dna = new Dna(Arrays.asList("CTGAGACT", "CTATGCAT", "TATTGTGT", "AGAGGGAT"));
        Assert.assertTrue(classificadorDeDna.isSimio(dna));
    }

    /**
     * C T G A
     * C T G T
     * T A T T
     * A G A G
     */
    @Test
    public void is_not_simio_test() {
        Dna dna = new Dna(Arrays.asList("CTGA", "CTGT ", "TATT ", "AGAG"));
        Assert.assertFalse(classificadorDeDna.isSimio(dna));
    }
}
