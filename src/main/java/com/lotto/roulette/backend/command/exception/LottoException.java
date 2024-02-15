package com.lotto.roulette.backend.command.exception;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum LottoException implements ErrorCode {

    DUPLICATE_LOTTO_HISTORY(HttpStatus.BAD_REQUEST, "이미 존재하는 로또 당첨 이력입니다."),
    NOT_EXISTS_PROVIDER(HttpStatus.NOT_FOUND, "존재하지 않는 번호 생성 방식입니다."),
    ;

    private final HttpStatusCode httpStatusCode;
    private final String errorMessage;
}
