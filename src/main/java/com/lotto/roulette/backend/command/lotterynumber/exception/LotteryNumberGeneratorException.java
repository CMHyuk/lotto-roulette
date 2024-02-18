package com.lotto.roulette.backend.command.lotterynumber.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum LotteryNumberGeneratorException {

    NOT_EXISTS_PROVIDER(HttpStatus.NOT_FOUND, "존재하지 않는 번호 생성 방식입니다."),
    ;

    private final HttpStatusCode httpStatusCode;
    private final String errorMessage;
}
