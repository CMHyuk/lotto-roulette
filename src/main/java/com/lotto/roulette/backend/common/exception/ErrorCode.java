package com.lotto.roulette.backend.common.exception;

public interface ErrorCode {

    int getValue();

    int getHttpStatusCode();

    String getErrorMessage();
}
