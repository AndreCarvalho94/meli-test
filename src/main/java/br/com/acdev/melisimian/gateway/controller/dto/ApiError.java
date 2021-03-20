package br.com.acdev.melisimian.gateway.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private String error;
    private String message;
    private Integer code;
    private LocalDateTime timestamp;
}
