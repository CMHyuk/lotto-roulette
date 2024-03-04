package com.lotto.roulette.backend.common.exception.presntation;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InternalServerErrorCode implements ErrorCode {

    private final int value = 10001;
    private final int httpStatusCode = 500;
    private final String message;

    public InternalServerErrorCode(String message) {
        this.message = message;
    }
}
