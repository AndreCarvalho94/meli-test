package br.com.acdev.melisimian.usecase.impl;

import br.com.acdev.melisimian.domain.Dna;
import br.com.acdev.melisimian.domain.Pontuacao;
import br.com.acdev.melisimian.usecase.ClassificadorDeDna;

public class ClassificadorDeDnaImpl implements ClassificadorDeDna {

    public boolean isSimio(Dna dna) {
        return computarPontuacao(dna).getPontuacao() > 2;
    }

    public Pontuacao computarPontuacao(Dna dna) {
        Pontuacao pontuacaoTotal = new Pontuacao();

        for (int i = 0; i < dna.tamanhoSequencia(); i++) {
            Pontuacao p1 = verificaIgualdadeEmSequenciaHorizontal(i, dna);
            pontuacaoTotal.merge(p1);
        }

        for (int j = 0; j < dna.tamanhoPalavras(); j++) {
            Pontuacao p1 = verificaIgualdadeEmSequenciaVertical(j, dna);
            pontuacaoTotal.merge(p1);
        }

        return pontuacaoTotal;
    }

    public Pontuacao verificaIgualdadeEmSequenciaHorizontal(int linha, Dna dna) {
        Pontuacao pontuacao = new Pontuacao();
        int contIguais = 0;
        int coluna = 0;
        while (coluna < dna.tamanhoPalavras() - 1) {
            char charAtual = dna.get(linha, coluna);
            char charProximo = dna.get(linha, coluna + 1);
            contIguais = charAtual == charProximo ? contIguais + 1 : 0;
            if (contIguais == 3) {
                pontuacao.pontuar(charAtual);
                contIguais = 0;
                coluna++;
            }
            coluna++;
        }
        return pontuacao;
    }

    public Pontuacao verificaIgualdadeEmSequenciaVertical(int coluna, Dna dna) {
        Pontuacao pontuacao = new Pontuacao();
        int contIguais = 0;
        int linha = 0;
        while (linha < dna.tamanhoSequencia() - 1) {
            char charAtual = dna.get(linha, coluna);
            char charProximo = dna.get(linha + 1, coluna);
            contIguais = charAtual == charProximo ? contIguais + 1 : 0;
            if (contIguais == 3) {
                pontuacao.pontuar(charAtual);
                contIguais = 0;
                linha++;
            }
            linha++;
        }
        return pontuacao;
    }

}
