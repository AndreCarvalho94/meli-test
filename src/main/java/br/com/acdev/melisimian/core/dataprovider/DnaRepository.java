package br.com.acdev.melisimian.core.dataprovider;

import br.com.acdev.melisimian.core.entity.DnaEntity;
import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.core.model.Estatisticas;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface DnaRepository {

    DnaEntity salvar(Dna dna, boolean isSimio);

    Estatisticas consultarEstatisticas();

}
