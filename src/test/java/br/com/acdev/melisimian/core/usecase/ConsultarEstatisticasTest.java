package br.com.acdev.melisimian.core.usecase;

import br.com.acdev.melisimian.core.dataprovider.DnaRepository;
import br.com.acdev.melisimian.core.model.Estatisticas;
import br.com.acdev.melisimian.core.usecase.impl.ConsultarEstatisticasImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsultarEstatisticasTest {

    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private ConsultarEstatisticasImpl consultarEstatisticas;

    @Test
    public void deve_consultar_estatisticas() {
        when(dnaRepository.consultarEstatisticas()).thenReturn(new Estatisticas(111L,10L));
        consultarEstatisticas.executar();
        verify(dnaRepository,times(1)).consultarEstatisticas();
    }
}
