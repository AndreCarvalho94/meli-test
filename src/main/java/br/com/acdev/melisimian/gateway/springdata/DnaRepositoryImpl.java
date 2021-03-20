package br.com.acdev.melisimian.gateway.springdata;

import br.com.acdev.melisimian.core.dataprovider.DnaRepository;
import br.com.acdev.melisimian.core.entity.DnaEntity;
import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.core.model.Estatisticas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DnaRepositoryImpl implements DnaRepository {

    @Autowired
    private DnaRepositorySpringData dnaRepositorySpringData;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public DnaEntity salvar(Dna dna, boolean isSimio) {
        DnaEntity dnaEntity = new DnaEntity();
        dnaEntity.setSequenciamento(String.join(",", dna.getSequencia()));
        dnaEntity.setSimio(true);
        return dnaRepositorySpringData.save(dnaEntity);
    }

    @Override
    public Estatisticas consultarEstatisticas() {
        String selectSimios =
                "SELECT COUNT(0)" +
                "  FROM dna" +
                " WHERE dna.is_simian = true";
        String sql =
               " SELECT COUNT(0) AS TOTAL, (" + selectSimios + ") AS SIMIOS" +
                "  FROM  dna ";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Estatisticas(
                        rs.getLong("SIMIOS"),
                        rs.getLong("TOTAL") - rs.getLong("SIMIOS"),
                        rs.getLong("SIMIOS") / rs.getLong("TOTAL"))
        );
    }
}
