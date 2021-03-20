package br.com.acdev.melisimian.gateway.springdata;

import br.com.acdev.melisimian.core.dataprovider.DnaRepository;
import br.com.acdev.melisimian.core.entity.DnaEntity;
import br.com.acdev.melisimian.core.model.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DnaRepositoryImpl implements DnaRepository {

    @Autowired
    private DnaRepositorySpringData dnaRepositorySpringData;

    @Override
    @Transactional
    public DnaEntity salvar(Dna dna, boolean isSimio) {
        DnaEntity dnaEntity = new DnaEntity();
        dnaEntity.setSequenciamento(String.join(",", dna.getSequencia()));
        dnaEntity.setSimio(true);
        return dnaRepositorySpringData.save(dnaEntity);
    }
}
