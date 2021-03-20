package br.com.acdev.melisimian.core.usecase.impl;

import br.com.acdev.melisimian.core.dataprovider.DnaRepository;
import br.com.acdev.melisimian.core.model.Estatisticas;
import br.com.acdev.melisimian.core.usecase.ConsultarEstatisticas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultarEstatisticasImpl implements ConsultarEstatisticas {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public Estatisticas executar() {
        return dnaRepository.consultarEstatisticas();
    }
}
