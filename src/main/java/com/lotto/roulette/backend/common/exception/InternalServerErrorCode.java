package com.lotto.roulette.backend.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class InternalServerErrorCode implements ErrorCode {

    private final HttpStatusCode httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    private final String errorMessage;

    public InternalServerErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
