package br.com.acdev.melisimian.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Estatisticas {

    private Long countSimianDna;
    private Long countHumanDna;
    private Long ratio;

}
