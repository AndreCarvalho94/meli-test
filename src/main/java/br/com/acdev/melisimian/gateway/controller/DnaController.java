package br.com.acdev.melisimian.gateway.controller;

import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.core.usecase.ClassificadorDeDna;
import br.com.acdev.melisimian.gateway.controller.dto.DnaRequest;
import br.com.acdev.melisimian.gateway.controller.dto.IsSimianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DnaController {

    @Autowired
    private ClassificadorDeDna classificadorDeDna;

    @PostMapping("/simian")
    public ResponseEntity<IsSimianResponse> cadastrarDna(@RequestBody DnaRequest dna) {
        Dna sequencia = new Dna(dna.getDna());
        return ResponseEntity.ok(new IsSimianResponse(classificadorDeDna.isSimio(sequencia)));
    }
}
