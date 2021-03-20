package br.com.acdev.melisimian.gateway.controller.exceptionshandler;


import br.com.acdev.melisimian.core.exceptions.DnaJaExistenteException;
import br.com.acdev.melisimian.gateway.controller.dto.ApiError;
import br.com.acdev.melisimian.gateway.exceptions.CriacaoDeBlobException;
import br.com.acdev.melisimian.gateway.exceptions.EntradaInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(CriacaoDeBlobException.class)
    public ResponseEntity<ApiError> erroNoBlob(CriacaoDeBlobException cbex) {
        ApiError error = new ApiError(DnaJaExistenteException.class.getSimpleName(), cbex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DnaJaExistenteException.class)
    public ResponseEntity<ApiError> dnaJaExistente(DnaJaExistenteException dex) {
        ApiError error = new ApiError(DnaJaExistenteException.class.getSimpleName(), dex.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntradaInvalidaException.class)
    public ResponseEntity<ApiError> entradaInvalida(EntradaInvalidaException dex) {
        ApiError error = new ApiError(EntradaInvalidaException.class.getSimpleName(), dex.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
