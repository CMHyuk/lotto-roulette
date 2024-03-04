package com.lotto.roulette.backend.common.exception.presntation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleUnExpectedException(Exception exception) {
        InternalServerErrorCode errorCode = new InternalServerErrorCode(exception.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ExceptionResponse.from(errorCode));
    }
}
