package com.lotto.roulette.backend.common.exception;

public interface ErrorCode {

    int getHttpStatusCode();

    String getErrorMessage();
}
