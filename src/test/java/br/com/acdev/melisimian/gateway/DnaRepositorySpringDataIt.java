package br.com.acdev.melisimian.gateway;

import br.com.acdev.melisimian.components.CalculadorDeHash;
import br.com.acdev.melisimian.components.impl.Sha256HashCalculador;
import br.com.acdev.melisimian.core.entity.DnaEntity;
import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.core.model.Estatisticas;
import br.com.acdev.melisimian.gateway.springdata.DnaRepositoryImpl;
import br.com.acdev.melisimian.gateway.springdata.DnaRepositorySpringData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class DnaRepositorySpringDataIt {

    @Autowired
    private DnaRepositorySpringData dnaRepositorySpringData;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CalculadorDeHash calculadorDeHash = new Sha256HashCalculador();

    private DnaRepositoryImpl dnaRepository;

    @Before
    public void setup() {
        dnaRepository = new DnaRepositoryImpl(dnaRepositorySpringData, jdbcTemplate, calculadorDeHash);
    }

    @Test
    public void deve_consultar_classificacao_test() {
        Estatisticas estatisticas = dnaRepository.consultarEstatisticas();
        Assert.assertEquals(4, estatisticas.getCountSimianDna(), 0);
        Assert.assertEquals(3, estatisticas.getCountHumanDna(), 0);
        Assert.assertEquals(4.0 / 3.0, estatisticas.getRatio(), 3);
    }

    @Test
    public void deve_salvar_dna() {
        Dna dna = new Dna(Arrays.asList("CTGA", "CTGT ", "TATT ", "AGAG"));
        DnaEntity dnaEntity = dnaRepository.salvar(dna, false);
        Assert.assertFalse(dnaEntity.isSimio());
        Assert.assertNotNull(dnaEntity.getHash());
        Assert.assertNotNull(dnaEntity.getId());
        Assert.assertNotNull(dnaEntity.getSequenciamento());
    }


}
