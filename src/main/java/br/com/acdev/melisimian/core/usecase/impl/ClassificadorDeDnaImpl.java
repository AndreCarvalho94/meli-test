package br.com.acdev.melisimian.core.usecase.impl;

import br.com.acdev.melisimian.core.dataprovider.DnaRepository;
import br.com.acdev.melisimian.core.model.Dna;
import br.com.acdev.melisimian.core.model.Pontuacao;
import br.com.acdev.melisimian.core.usecase.ClassificadorDeDna;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClassificadorDeDnaImpl implements ClassificadorDeDna {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public boolean isSimio(Dna dna) {
        boolean isSimio = computarPontuacao(dna).getPontuacao() > 1;
        dnaRepository.salvar(dna, isSimio);
        return isSimio;
    }

    public Pontuacao computarPontuacao(Dna dna) {
        Pontuacao pontuacaoTotal = new Pontuacao();
        Pontuacao p1 = verificarIgualdadeEmSequenciaHorizontal(dna);
        Pontuacao p2 = verificarIgualdadeEmSequenciaVertical(dna);
        Pontuacao p3 = verificarIgualdadeEmDiagonalPrimaria(dna);
        Pontuacao p4 = verificaIgualdadeEmDiagonalSecundaria(dna);
        pontuacaoTotal.merge(p1);
        pontuacaoTotal.merge(p2);
        pontuacaoTotal.merge(p3);
        pontuacaoTotal.merge(p4);
        return pontuacaoTotal;
    }

    public Pontuacao verificarIgualdadeEmSequenciaHorizontal(Dna dna) {
        Pontuacao pontuacaoTotal = new Pontuacao();
        for (int i = 0; i < dna.tamanhoSequencia(); i++) {
            Pontuacao p1 = verificaIgualdadeEmDirecao(dna, i, 0, 0, 1, dna.tamanhoPalavras());
            pontuacaoTotal.merge(p1);
        }
        return pontuacaoTotal;
    }

    public Pontuacao verificarIgualdadeEmSequenciaVertical(Dna dna) {
        Pontuacao pontuacaoTotal = new Pontuacao();
        for (int j = 0; j < dna.tamanhoPalavras(); j++) {
            Pontuacao p1 = verificaIgualdadeEmDirecao(dna, 0, j, 1, 0, dna.tamanhoSequencia());
            pontuacaoTotal.merge(p1);
        }
        return pontuacaoTotal;
    }

    public Pontuacao verificarIgualdadeEmDiagonalPrimaria(Dna dna) {
        Pontuacao pontuacaoTotal = new Pontuacao();
        for (int i = 0; i <= dna.tamanhoSequencia() - 4; i++) {
            Pontuacao p1 = verificaIgualdadeEmDirecao(dna, i, 0, 1, 1, dna.tamanhoSequencia());
            pontuacaoTotal.merge(p1);
        }
        for (int j = 1; j <= dna.tamanhoPalavras() - 4; j++) {
            Pontuacao p1 = verificaIgualdadeEmDirecao(dna, 0, j, 1, 1, dna.tamanhoPalavras());
            pontuacaoTotal.merge(p1);
        }
        return pontuacaoTotal;
    }

    public Pontuacao verificaIgualdadeEmDiagonalSecundaria(Dna dna) {
        Pontuacao pontuacaoTotal = new Pontuacao();
        for (int j = 3; j < dna.tamanhoPalavras(); j++) {
            Pontuacao p1 = verificaIgualdadeEmDirecao(dna, 0, j, 1, -1, dna.tamanhoPalavras());
            pontuacaoTotal.merge(p1);
        }
        for (int i = 1; i < dna.tamanhoSequencia() - 4; i++) {
            Pontuacao p1 = verificaIgualdadeEmDirecao(dna, i, dna.tamanhoPalavras() - 1, 1, -1, dna.tamanhoSequencia());
            pontuacaoTotal.merge(p1);
        }

        return pontuacaoTotal;
    }

    public Pontuacao verificaIgualdadeEmDirecao(Dna dna,
                                                int linhaInicial,
                                                int colunaInicial,
                                                int incrementoLinha,
                                                int incrementoColuna,
                                                int limiteIncremento) {
        Pontuacao pontuacao = new Pontuacao();
        int contIguais, incremento, linha, coluna;
        contIguais = incremento = 0;
        while (incremento < limiteIncremento - 1) {
            linha = linhaInicial + (incremento * incrementoLinha);
            coluna = colunaInicial + (incremento * incrementoColuna);
            if (condicaoLinhaOuColunaOutOfRange(linha, coluna, incrementoLinha, incrementoColuna, dna)) {
                break;
            }
            char charAtual = dna.get(linha, coluna);
            char charProximo = dna.get(linha + incrementoLinha, coluna + incrementoColuna);
            contIguais = charAtual == charProximo ? contIguais + 1 : 0;
            if (contIguais == 3) {
                pontuacao.pontuar(charAtual);
                contIguais = 0;
                incremento++;
            }
            incremento++;
        }
        return pontuacao;
    }

    public boolean condicaoLinhaOuColunaOutOfRange(int linha, int coluna, int incrementoLinha, int incrementoColuna, Dna dna) {
        return linha + incrementoLinha >= dna.tamanhoSequencia() ||
                linha + incrementoLinha < 0 ||
                coluna + incrementoColuna >= dna.tamanhoPalavras() ||
                coluna + incrementoColuna < 0;
    }
}
