package br.com.acdev.melisimian.gateway.controller;

import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.core.model.Estatisticas;
import br.com.acdev.melisimian.core.usecase.ClassificadorDeDna;
import br.com.acdev.melisimian.core.usecase.ConsultarEstatisticas;
import br.com.acdev.melisimian.gateway.controller.dto.DnaRequest;
import br.com.acdev.melisimian.gateway.controller.dto.IsSimianResponse;
import br.com.acdev.melisimian.gateway.controller.dto.StatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DnaController {

    @Autowired
    private ClassificadorDeDna classificadorDeDna;

    @Autowired
    private ConsultarEstatisticas consultarEstatisticas;

    @PostMapping("/simian")
    public ResponseEntity<IsSimianResponse> cadastrarDna(@RequestBody DnaRequest dna) {
        Dna sequencia = new Dna(dna.getDna());
        return ResponseEntity.ok(new IsSimianResponse(classificadorDeDna.isSimio(sequencia)));
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> consultarStats(){
        Estatisticas estatisticas = consultarEstatisticas.executar();
        return ResponseEntity.ok(new StatsResponse(estatisticas));
    }

}
