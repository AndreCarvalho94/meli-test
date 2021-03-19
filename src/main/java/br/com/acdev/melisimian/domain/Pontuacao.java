package br.com.acdev.melisimian.domain;

import java.util.HashMap;
import java.util.Map;

public class Pontuacao {

    private Map<Character, Integer> pontuacao;

    public Pontuacao() {
        this.pontuacao = new HashMap<>();
        this.pontuacao.put('A', 0);
        this.pontuacao.put('T', 0);
        this.pontuacao.put('C', 0);
        this.pontuacao.put('G', 0);
    }

    public void merge(Pontuacao p1) {
        p1.getPontos().forEach((k, v) -> pontuacao.merge(k, v, Integer::sum));
    }

    public void pontuar(char base) {
        int pontos = getPontuacao(base) + 1;
        this.pontuacao.put(base, pontos);
    }

    public int getPontuacao(char base) {
        return this.pontuacao.get(base);
    }

    public int getPontuacao() {
        return this.pontuacao.get('A') +
                this.pontuacao.get('T') +
                this.pontuacao.get('C') +
                this.pontuacao.get('G');
    }

    public HashMap<Character, Integer> getPontos() {
        return new HashMap(pontuacao);
    }
}
