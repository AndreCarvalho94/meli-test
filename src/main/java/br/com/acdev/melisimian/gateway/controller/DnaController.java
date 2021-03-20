package br.com.acdev.melisimian.gateway.controller;

import br.com.acdev.melisimian.domain.Dna;
import br.com.acdev.melisimian.gateway.controller.response.IsSimianResponse;
import br.com.acdev.melisimian.usecase.ClassificadorDeDna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DnaController {

    @Autowired
    private ClassificadorDeDna classificadorDeDna;

    @PostMapping("/simian")
    public ResponseEntity<IsSimianResponse> cadastrarDna(@RequestBody List<String> dna) {
        Dna sequencia = new Dna(dna);
        return ResponseEntity.ok(new IsSimianResponse(classificadorDeDna.isSimio(sequencia)));
    }
}
