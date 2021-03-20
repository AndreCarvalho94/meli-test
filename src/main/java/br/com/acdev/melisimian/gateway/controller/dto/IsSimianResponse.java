package br.com.acdev.melisimian.gateway.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IsSimianResponse {

    @JsonProperty("is_simian")
    boolean isSimian;

}
