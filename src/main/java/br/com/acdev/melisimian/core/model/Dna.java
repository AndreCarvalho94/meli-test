package br.com.acdev.melisimian.core.model;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class Dna {

    List<String> sequencia;

    public char get(int linha, int coluna) {
        return sequencia.get(linha).charAt(coluna);
    }

    public int tamanhoSequencia() {
        return sequencia.size();
    }

    public int tamanhoPalavras() {
        return sequencia.get(0).length();
    }

    public List<String> getSequencia() {
        return Collections.unmodifiableList(sequencia);
    }
}
