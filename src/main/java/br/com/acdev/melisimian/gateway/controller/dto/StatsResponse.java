package br.com.acdev.melisimian.gateway.controller.dto;

import br.com.acdev.melisimian.core.model.Estatisticas;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse {

    @JsonProperty("count_simian_dna")
    private Long countSimianDna;

    @JsonProperty("count_human_dna")
    private Long countHumanDna;

    @JsonProperty("ratio")
    private double ratio;

    public StatsResponse(Estatisticas estatisticas){
        this.countHumanDna = estatisticas.getCountHumanDna();
        this.countSimianDna = estatisticas.getCountSimianDna();
        this.ratio = estatisticas.getRatio();
    }
}