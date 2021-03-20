package br.com.acdev.melisimian.gateway.springdata;

import br.com.acdev.melisimian.components.CalculadorDeHash;
import br.com.acdev.melisimian.core.exceptions.DnaJaExistenteException;
import br.com.acdev.melisimian.core.dataprovider.DnaRepository;
import br.com.acdev.melisimian.core.entity.DnaEntity;
import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.core.model.Estatisticas;
import br.com.acdev.melisimian.gateway.exceptions.CriacaoDeBlobException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;
import java.sql.SQLException;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DnaRepositoryImpl implements DnaRepository {

    @Autowired
    private DnaRepositorySpringData dnaRepositorySpringData;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CalculadorDeHash calculadorDeHash;

    @Override
    @Transactional
    public DnaEntity salvar(Dna dna, boolean isSimio) {
        try {
            DnaEntity dnaEntity = new DnaEntity();
            String sequenciamento = String.join(",", dna.getSequencia());
            dnaEntity.setSequenciamento(new SerialBlob(sequenciamento.getBytes()));
            dnaEntity.setSimio(isSimio);
            dnaEntity.setHash(calculadorDeHash.executar(sequenciamento));
            return dnaRepositorySpringData.save(dnaEntity);
        } catch (SQLException ex) {
            throw new CriacaoDeBlobException();
        } catch (DataIntegrityViolationException dtex){
            throw new DnaJaExistenteException();
        }
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
                        rs.getLong("TOTAL") - rs.getLong("SIMIOS"))
        );
    }
}
