package com.lotto.roulette.backend.common.exception;

import lombok.Getter;

@Getter
public class InternalServerErrorCode implements ErrorCode {

    private final int httpStatusCode = 500;
    private final String errorMessage;

    public InternalServerErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
