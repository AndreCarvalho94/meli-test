package br.com.acdev.melisimian.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class PontuacaoTest {


    @Test
    public void pontuacao_inicia_zerada_test(){
        Pontuacao pontuacao = new Pontuacao();
        Assert.assertEquals(0, pontuacao.getPontuacao());
    }

    @Test
    public void incrementa_pontos_test(){
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.pontuar('A');
        pontuacao.pontuar('T');
        pontuacao.pontuar('C');
        pontuacao.pontuar('G');
        Assert.assertEquals(4, pontuacao.getPontuacao());
    }

    @Test
    public void incrementa_pontos_A_test(){
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.pontuar('A');
        pontuacao.pontuar('A');
        pontuacao.pontuar('A');
        Assert.assertEquals(3, pontuacao.getPontuacao('A'));
    }

    @Test
    public void cria_copia_dos_valores_dos_pontos_test(){
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.pontuar('A');
        pontuacao.pontuar('T');
        pontuacao.pontuar('C');
        pontuacao.pontuar('G');
        HashMap<Character, Integer> pontos = pontuacao.getPontos();
        pontuacao.pontuar('A');
        Assert.assertNotEquals(pontos, pontuacao.getPontos());
    }

    @Test
    public void cria_copia_dos_valores_dos_pontos_igual_test(){
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.pontuar('A');
        pontuacao.pontuar('T');
        pontuacao.pontuar('C');
        pontuacao.pontuar('G');
        HashMap<Character, Integer> pontos = pontuacao.getPontos();
        Assert.assertEquals(pontos, pontuacao.getPontos());
    }

    @Test
    public void merge_duas_pontuacoes_test(){
        Pontuacao p1 = new Pontuacao();
        p1.pontuar('A');
        p1.pontuar('T');
        p1.pontuar('C');
        p1.pontuar('G');
        Pontuacao p2 = new Pontuacao();
        p2.pontuar('A');
        p2.pontuar('T');
        p2.pontuar('C');
        p2.pontuar('G');
        p1.merge(p2);
        Assert.assertEquals(8, p1.getPontuacao());
    }

}
