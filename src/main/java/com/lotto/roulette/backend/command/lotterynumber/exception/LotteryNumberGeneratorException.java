package com.lotto.roulette.backend.command.lotterynumber.exception;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum LotteryNumberGeneratorException implements ErrorCode {

    NOT_EXISTS_PROVIDER(404, "존재하지 않는 번호 생성 방식입니다."),
    ;

    private final int httpStatusCode;
    private final String errorMessage;
}
