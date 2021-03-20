package br.com.acdev.melisimian.gateway.springdata;

import br.com.acdev.melisimian.core.entity.DnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepositorySpringData extends JpaRepository<DnaEntity, Long> {
}
