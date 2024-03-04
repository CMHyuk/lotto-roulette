package com.lotto.roulette.backend.common.exception.presntation;

import com.lotto.roulette.backend.common.exception.ErrorCode;

public record ExceptionResponse(Integer errorCode, String message) {

    public static ExceptionResponse from(ErrorCode errorCode) {
        return new ExceptionResponse(errorCode.getValue(), errorCode.getErrorMessage());
    }
}
