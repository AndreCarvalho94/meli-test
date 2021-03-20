package br.com.acdev.melisimian.gateway.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IsSimianResponse {

    @JsonProperty("is_simian")
    boolean isSimian;

}
