package br.com.acdev.melisimian.usecase.impl;

import br.com.acdev.melisimian.domain.Dna;
import br.com.acdev.melisimian.usecase.ClassificadorDeDna;

import java.util.HashMap;
import java.util.Map;

public class ClassificadorDeDnaImpl implements ClassificadorDeDna {

    Map<Character, Integer> pontuacao;

    public ClassificadorDeDnaImpl() {
        this.pontuacao = new HashMap<>();
        this.pontuacao.put('A', 0);
        this.pontuacao.put('T', 0);
        this.pontuacao.put('C', 0);
        this.pontuacao.put('G', 0);
    }

    public boolean isSimio(Dna dna) {
        return false;
    }

    public void verificaIgualdadeEmSequenciaHorizontal(int linha, Dna dna) {
        int contIguais = 0;
        int coluna = 0;
        while (coluna < dna.tamanhoPalavras() - 1) {
            char charAtual = dna.get(linha, coluna);
            char charProximo = dna.get(linha, coluna + 1);
            contIguais = charAtual == charProximo ? contIguais + 1 : 0;
            if (contIguais == 3) {
                int pontuacao = this.pontuacao.get(charAtual) + 1;
                this.pontuacao.put(charAtual, pontuacao);
                contIguais = 0;
                coluna++;
            }
            coluna++;
        }
    }

    public int getPontuacao(char base) {
        return this.pontuacao.get(base);
    }
}
