package com.lotto.roulette.backend.common.exception;

import org.springframework.http.HttpStatusCode;

public interface ErrorCode {

    HttpStatusCode getHttpStatusCode();

    String getErrorMessage();
}
