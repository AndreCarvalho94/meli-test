package br.com.acdev.melisimian.components;


import br.com.acdev.melisimian.components.impl.Sha256HashCalculador;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Sha256HashCalculador.class)
public class CalculadorDeHashTest {

    @Autowired
    private CalculadorDeHash calculadorDeHash;

    @Test
    public void deve_calcular_o_mesmo_hash() {
        String src1 = "ATCGATCA,TATCGATC,TTATGACG,CGTGACTA,TTGACGGT,TGACGAGG";
        String src2 = "ATCGATCA,TATCGATC,TTATGACG,CGTGACTA,TTGACGGT,TGACGAGG";
        String hash1 = calculadorDeHash.executar(src1);
        String hash2 = calculadorDeHash.executar(src2);
        Assert.assertEquals(hash1, hash2);

    }

    @Test
    public void deve_calcular_hashs_diferentes() {
        String src1 = "ATCGATCA,TATCGATC,TTATGACG,CGTGACTA,TTGACGGT,TGACGAGG";
        String src2 = "TTCGATCA,TATCGATC,TTATGACG,CGTGACTA,TTGACGGT,TGACGAGG";
        String hash1 = calculadorDeHash.executar(src1);
        String hash2 = calculadorDeHash.executar(src2);
        Assert.assertNotEquals(hash1, hash2);

    }
}
