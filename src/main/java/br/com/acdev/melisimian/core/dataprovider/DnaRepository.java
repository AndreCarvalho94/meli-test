package br.com.acdev.melisimian.core.dataprovider;

import br.com.acdev.melisimian.core.entity.DnaEntity;
import br.com.acdev.melisimian.core.model.Dna;
import org.springframework.stereotype.Service;

@Service
public interface DnaRepository {

    DnaEntity salvar(Dna dna, boolean isSimio);

}
