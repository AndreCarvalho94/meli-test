package br.com.acdev.melisimian.gateway;

import br.com.acdev.melisimian.core.entity.DnaEntity;
import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.gateway.springdata.DnaRepositoryImpl;
import br.com.acdev.melisimian.gateway.springdata.DnaRepositorySpringData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DnaRepositorySpringDataTest {

    @Mock
    DnaRepositorySpringData repository;

    @InjectMocks
    DnaRepositoryImpl dnaRepository;

    @Test
    public void salvar_test(){
        when(repository.save(any(DnaEntity.class))).thenReturn(new DnaEntity("", true, 1L));
        Dna dna = new Dna(Arrays.asList("ATCG", "ATCG", "ATCG"));
        DnaEntity dnaEntity = dnaRepository.salvar(dna, true);
        Assert.assertTrue(dnaEntity.isSimio());
    }


}
