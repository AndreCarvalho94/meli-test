package br.com.acdev.melisimian.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Estatisticas {

    private Long countSimianDna;
    private Long countHumanDna;

    public double getRatio(){
        return (double)countSimianDna/(double)(countSimianDna+countSimianDna);
    }
}
