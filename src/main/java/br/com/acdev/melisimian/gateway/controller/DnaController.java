package br.com.acdev.melisimian.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DnaController {

    @PostMapping("/simian")
    public ResponseEntity<String> cadastrarDna() {
        return ResponseEntity.ok("{is_simian:true}");
    }
}
