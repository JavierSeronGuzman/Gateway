package com.example.gameserviceapi.controller.impl;


import com.example.gameserviceapi.commons.exceptions.GameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import com.example.gameserviceapi.commons.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j //para los logs de la aplicacion
public class ExceptionHandlerController {
    @ExceptionHandler(value = {GameException.class})
    ResponseEntity<ErrorResponse> handleError(GameException gameException) {
        log.error("new Ecxeption", gameException);
        var errorResponse = ErrorResponse.builder()
                .codeStatus(gameException.getHttpStatus().value())
                .message(gameException.getMessage())
                .build();

        return  ResponseEntity.status(gameException.getHttpStatus()).body(errorResponse);
    }
}
